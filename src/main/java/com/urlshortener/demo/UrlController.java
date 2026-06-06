package com.urlshortener.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    // Endpoint to create a short URL
    @PostMapping("/api/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String originalUrl) {

        if (!isValidUrl(originalUrl)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: Please provide a valid URL (e.g., https://www.google.com)");
        }

        String shortCode = urlService.shortenUrl(originalUrl);
        return ResponseEntity.ok("http://localhost:8080/" + shortCode);
    }

    // Endpoint to redirect the user
    @GetMapping("/{shortCode:[a-zA-Z0-9]+}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortCode) {

        if ("favicon.ico".equals(shortCode) || "error".equals(shortCode)) {
            return ResponseEntity.notFound().build();
        }

        String originalUrl = urlService.getOriginalUrl(shortCode);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }

    // Endpoint to check single link stats
    @GetMapping("/api/analytics/{shortCode}")
    public ResponseEntity<String> getLinkAnalytics(@PathVariable String shortCode) {
        UrlMapping mapping = urlService.getLinkStats(shortCode);

        return ResponseEntity.ok("📈 LINK STATISTICS" +
                "\n------------------------" +
                "\nShort Code:   " + mapping.getShortCode() +
                "\nOriginal URL: " + mapping.getOriginalUrl() +
                "\nTotal Clicks: " + mapping.getClicks());
    }

    // 📊 NEW: Endpoint to get all shortened links as JSON data
    @GetMapping("/api/links")
    public ResponseEntity<List<UrlMapping>> getAllLinks() {
        return ResponseEntity.ok(urlService.getAllLinks());
    }

    // Helper method to validate the URL format
    private boolean isValidUrl(String url) {
        try {
            URI uri = new URI(url);
            uri.toURL();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
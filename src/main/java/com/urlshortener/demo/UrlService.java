package com.urlshortener.demo;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    // The 62 characters used to make our short links
    private static final String ALLOWED_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALLOWED_CHARACTERS.length();

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String originalUrl) {
        // 1. Save the original URL to get an auto-generated ID
        UrlMapping mapping = new UrlMapping();
        mapping.setOriginalUrl(originalUrl);
        mapping = urlRepository.save(mapping);

        // 2. Convert that ID into a Base62 short code
        String shortCode = encodeIdToBase62(mapping.getId());

        // 3. Update the database entry with the new short code
        mapping.setShortCode(shortCode);
        urlRepository.save(mapping);

        return shortCode;
    }

    public String getOriginalUrl(String shortCode) {
        // 1. Find the URL mapping in the database
        UrlMapping mapping = urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found!"));

        // 2. 📊 Increment the click count by 1
        mapping.setClicks(mapping.getClicks() + 1);

        // 3. Save the updated mapping back to MySQL
        urlRepository.save(mapping);

        // 4. Return the destination URL for redirection
        return mapping.getOriginalUrl();
    }

    // Our Base62 Conversion Algorithm
    private String encodeIdToBase62(long id) {
        if (id == 0) return String.valueOf(ALLOWED_CHARACTERS.charAt(0));

        StringBuilder shortUrl = new StringBuilder();
        while (id > 0) {
            shortUrl.append(ALLOWED_CHARACTERS.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return shortUrl.reverse().toString();
    }

    public UrlMapping getLinkStats(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .orElseThrow(() -> new RuntimeException("URL not found!"));
    }

    // 📊 NEW: Grab every single link from the database for our dashboard
    public List<UrlMapping> getAllLinks() {
        return urlRepository.findAll();
    }
}
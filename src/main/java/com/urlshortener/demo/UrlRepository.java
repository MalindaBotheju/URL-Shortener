package com.urlshortener.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlMapping, Long> {
    // This custom method allows us to find a long URL using the short code
    Optional<UrlMapping> findByShortCode(String shortCode);
}
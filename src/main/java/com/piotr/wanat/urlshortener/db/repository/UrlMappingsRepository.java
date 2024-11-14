package com.piotr.wanat.urlshortener.db.repository;

import com.piotr.wanat.urlshortener.db.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UrlMappingsRepository extends JpaRepository<UrlMapping, Long> {

    void deleteByShortUrl(String shortUrl);

    @Query("SELECT m.url FROM UrlMapping m " +
            "where m.shortUrl = :shortUrl " +
            "and (m.expirationDate > CURRENT_TIMESTAMP " +
            "or m.expirationDate is null)")
    Optional<String> getByShortUrl(String shortUrl);

    @Modifying
    @Query("DELETE FROM UrlMapping m  WHERE m.expirationDate < CURRENT_TIMESTAMP")
    int deleteExpired();

    boolean existsByShortUrl(String shortUrl);
}

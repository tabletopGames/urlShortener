package com.piotr.wanat.urlshortener.rest.dto;

import com.piotr.wanat.urlshortener.validation.ValidExpiration;
import com.piotr.wanat.urlshortener.validation.ValidUrl;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidExpiration
@ValidUrl
public class UrlMappingDto extends SimpleMappingDto {

    public UrlMappingDto(String url, String shortUrl) {
        super(url);
        this.shortUrl = shortUrl;
    }

    public UrlMappingDto(String url, String shortUrl, LocalDateTime expirationDate) {
        super(url);
        this.shortUrl = shortUrl;
        this.expirationDate = expirationDate;
    }

    private String shortUrl;
    private Long ttl;
    private LocalDateTime expirationDate;
}

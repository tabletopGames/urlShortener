package com.piotr.wanat.urlshortener.service;

import com.piotr.wanat.urlshortener.db.entity.UrlMapping;
import com.piotr.wanat.urlshortener.db.repository.UrlMappingsRepository;
import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto;
import com.piotr.wanat.urlshortener.util.ShortUrlGenerator;
import com.piotr.wanat.urlshortener.validation.exceptions.UniqueUrlException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UrlMappingsService {

    @Autowired
    private final UrlMappingsRepository urlMappingsRepository;

    @Autowired
    private final UrlMappingsConverter urlMappingsConverter;

    @Autowired
    private final ShortUrlGenerator shortUrlGenerator;

    public String createSimpleMapping(String url) {
        String shortUrl = shortUrlGenerator.generateUniqueId();
        if (urlMappingsRepository.existsByShortUrl(shortUrl)) {
            createSimpleMapping(url);
        }
        urlMappingsRepository.save(new UrlMapping(url, shortUrl));
        return shortUrl;
    }

    public String createMapping(UrlMappingDto urlMappingDto) {

        if (urlMappingDto.getShortUrl() == null) {
            String shortUrl = shortUrlGenerator.generateUniqueId();
            if (urlMappingsRepository.existsByShortUrl(shortUrl)) {
                createMapping(urlMappingDto);
            }
            urlMappingDto.setShortUrl(shortUrl);
        } else {
            checkIfExists(urlMappingDto);
        }
        handleTtl(urlMappingDto);

        UrlMapping createdMapping = urlMappingsRepository.save(urlMappingsConverter.convertToEntity(urlMappingDto));
        log.debug("Successfully created mapping: [{}]", createdMapping);

        return urlMappingDto.getShortUrl();
    }

    private void populateShortUrl(UrlMappingDto urlMappingDto, String url) {

    }
    @Transactional
    public void deleteMappingByShortUrl(String shortUrl) {
        urlMappingsRepository.deleteByShortUrl(shortUrl);
    }

    private static void handleTtl(UrlMappingDto urlMappingDto) {
        if (urlMappingDto.getTtl() != null) {
            urlMappingDto.setExpirationDate(LocalDateTime.now().plusSeconds(urlMappingDto.getTtl()));
        }
    }

    private void checkIfExists(UrlMappingDto urlMappingDto) {
        if (urlMappingsRepository.existsByShortUrl(urlMappingDto.getShortUrl())) {
            throw new UniqueUrlException(urlMappingDto.getShortUrl());
        }
    }
}

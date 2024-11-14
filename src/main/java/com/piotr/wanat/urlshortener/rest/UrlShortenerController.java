package com.piotr.wanat.urlshortener.rest;

import com.piotr.wanat.urlshortener.rest.dto.SimpleMappingDto;
import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto;
import com.piotr.wanat.urlshortener.service.UrlMappingsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "urlShortener", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
@Slf4j
public class UrlShortenerController {

    @Autowired
    private final UrlMappingsService urlMappingsService;

    @PostMapping
    public String createSimpleMapping(@Valid @RequestBody SimpleMappingDto simpleMappingDto) {
        log.info("Attempting to create a new simple mapping for url: [{}]", simpleMappingDto.getUrl());
        return urlMappingsService.createSimpleMapping(simpleMappingDto.getUrl());
    }

    @PostMapping("/userDefined")
    public String createMapping(@Valid @RequestBody UrlMappingDto urlMappingDto) {
        log.info("Attempting to create a new mapping for parameters: [{}]", urlMappingDto);
        return urlMappingsService.createMapping(urlMappingDto);
    }

    @DeleteMapping("{id}")
    public void deleteMapping(@PathVariable("id") String id) {
        log.info("Attempting to delete a mapping with id: [{}]", id);
        urlMappingsService.deleteMappingByShortUrl(id);
    }
}

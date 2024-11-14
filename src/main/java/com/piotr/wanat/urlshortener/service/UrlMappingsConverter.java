package com.piotr.wanat.urlshortener.service;

import com.piotr.wanat.urlshortener.db.entity.UrlMapping;
import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UrlMappingsConverter {

    @Autowired
    private final ModelMapper modelMapper;

    public UrlMapping convertToEntity(UrlMappingDto urlMappingDto) {
        return modelMapper.map(urlMappingDto, UrlMapping.class);
    }
}
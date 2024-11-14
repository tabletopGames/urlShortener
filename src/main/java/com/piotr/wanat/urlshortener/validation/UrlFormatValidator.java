package com.piotr.wanat.urlshortener.validation;

import com.piotr.wanat.urlshortener.rest.dto.SimpleMappingDto;
import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFormatValidator implements ConstraintValidator<ValidUrl, SimpleMappingDto> {

    private static final String URL_PATTERN = "^(https?):\\/\\/(?!localhost|127\\.0\\.0\\.1)([\\w-]+\\.)+[\\w-]+(\\/.*)?$";

    @Override
    public boolean isValid(SimpleMappingDto simpleMappingDto, ConstraintValidatorContext context) {
        Pattern pattern = Pattern.compile(URL_PATTERN);
        Matcher matcher = pattern.matcher(simpleMappingDto.getUrl());
        return matcher.matches();
    }

    @Override
    public void initialize(ValidUrl constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}

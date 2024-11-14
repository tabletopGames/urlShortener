package com.piotr.wanat.urlshortener.validation;

import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExpirationDateValidator implements ConstraintValidator<ValidExpiration, UrlMappingDto> {

    @Override
    public boolean isValid(UrlMappingDto urlMappingDto, ConstraintValidatorContext context) {
        return !(urlMappingDto.getTtl() != null && urlMappingDto.getExpirationDate() != null);
    }

    @Override
    public void initialize(ValidExpiration constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}

package com.piotr.wanat.urlshortener.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlFormatValidator.class)
public @interface ValidUrl {

    String message() default "The provided url is in a wrong format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

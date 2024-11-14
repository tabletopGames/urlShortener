package com.piotr.wanat.urlshortener.validation.exceptions;

import static java.lang.String.format;

public class NotFoundShortUrlException extends RuntimeException {
    public NotFoundShortUrlException(String id) {
        super(format("Not found provided short url: [%s]", id));
    }
}
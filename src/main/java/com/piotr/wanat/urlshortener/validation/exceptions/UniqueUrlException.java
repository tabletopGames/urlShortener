package com.piotr.wanat.urlshortener.validation.exceptions;

import static java.lang.String.format;

public class UniqueUrlException extends RuntimeException {
    public UniqueUrlException(String shortUrl) {
        super(format("Provided short url: [%s] already exists", shortUrl));
    }
}

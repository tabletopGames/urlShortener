package com.piotr.wanat.urlshortener.service;

import com.piotr.wanat.urlshortener.db.repository.UrlMappingsRepository;
import com.piotr.wanat.urlshortener.validation.exceptions.NotFoundShortUrlException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.RedirectView;

@Component
@RequiredArgsConstructor
@Slf4j
public class RedirectService {

    @Autowired
    private final UrlMappingsRepository urlMappingsRepository;

    public RedirectView redirect(String shortUrl) {
        String url = urlMappingsRepository.getByShortUrl(shortUrl)
                .orElseThrow(() -> new NotFoundShortUrlException(shortUrl));

        RedirectView redirect = new RedirectView(url);
        log.info("Attempting to redirect by id: [{}] to: [{}]", shortUrl, url);
        return redirect;
    }
}

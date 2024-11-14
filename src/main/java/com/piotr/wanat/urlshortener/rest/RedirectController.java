package com.piotr.wanat.urlshortener.rest;

import com.piotr.wanat.urlshortener.service.RedirectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RedirectController {

    private final RedirectService redirectService;

    @GetMapping("redirect/{id}")
    public RedirectView redirect(@PathVariable String id) {
        log.info("Attempting to redirect by id: [{}]", id);
        return redirectService.redirect(id);
    }
}

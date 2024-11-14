package com.piotr.wanat.urlshortener

import com.piotr.wanat.urlshortener.db.repository.CounterRepository
import com.piotr.wanat.urlshortener.db.repository.UrlMappingsRepository
import com.piotr.wanat.urlshortener.rest.RedirectController
import com.piotr.wanat.urlshortener.rest.UrlShortenerController
import com.piotr.wanat.urlshortener.rest.dto.UrlMappingDto
import com.piotr.wanat.urlshortener.util.ShortUrlGenerator
import com.piotr.wanat.urlshortener.validation.exceptions.NotFoundShortUrlException
import com.piotr.wanat.urlshortener.validation.exceptions.UniqueUrlException
import org.junit.jupiter.api.AfterEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDateTime

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@SpringBootTest(classes = UrlShortenerApplication.class, webEnvironment = NONE)
@ActiveProfiles("test")
class RedirectIntegrationSpec extends Specification {

    @Autowired
    UrlShortenerController shortenerController

    @Autowired
    RedirectController redirectController

    @Autowired
    UrlMappingsRepository mappingsRepository

    @Autowired
    CounterRepository counterRepository

    @Autowired
    ShortUrlGenerator generator

    @Shared
    var url = 'url'

    @Shared
    var shortUrl = 'shortUrl'

    @AfterEach
    void cleanup() {
        mappingsRepository.deleteAll()
    }

    def 'should create mapping'() {
        given:
        shortenerController.createMapping(givenMapping)

        when:
        def redirect = redirectController.redirect(shortUrl)

        then:
        redirect.url == expectedValue

        where:
        givenMapping                                                       | expectedValue
        new UrlMappingDto(url, shortUrl)                                   | url
        new UrlMappingDto(url, shortUrl, LocalDateTime.now().plusDays(1))  | url
    }

    def 'should throw exception when short url not found'() {
        when:
        redirectController.redirect(shortUrl)

        then:
        thrown(NotFoundShortUrlException.class)
    }

    def 'should throw exception on creating the same mapping'() {
        given:
        def mapping = new UrlMappingDto(url, shortUrl)
        shortenerController.createMapping(mapping)

        when:
        shortenerController.createMapping(mapping)

        then:
        thrown(UniqueUrlException.class)
    }

    def 'should properly generate following ids'() {
        when:
        var generatedList = []
        for (int i = 0; i < 10; i++) {
            generatedList.add(generator.generateUniqueId())
        }
        then:
        generatedList == ['0','1','2','3','4','5','6','7','8','9']
    }
}

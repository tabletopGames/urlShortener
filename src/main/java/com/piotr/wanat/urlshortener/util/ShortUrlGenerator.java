package com.piotr.wanat.urlshortener.util;

import com.piotr.wanat.urlshortener.db.entity.Counter;
import com.piotr.wanat.urlshortener.db.repository.CounterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShortUrlGenerator {

    private static final String ALPHANUMERIC_CHARACTERS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final int BASE = ALPHANUMERIC_CHARACTERS.length();

    @Autowired
    CounterRepository counterRepository;

    public synchronized String generateUniqueId() {
        Counter counter = counterRepository.findById(1L).get();
        Long counterNumber = counter.getCounterNumber();
        String generatedId = toBase62(counterNumber);
        log.info("Generated id: [{}]", generatedId);
        counter.setCounterNumber(counterNumber + 1);
        counterRepository.save(counter);
        return generatedId;
    }

    private String toBase62(Long number) {
        StringBuilder idBuilder = new StringBuilder();

        do {
            int remainder = (int) (number % BASE);
            idBuilder.append(ALPHANUMERIC_CHARACTERS.charAt(remainder));
            number /= BASE;
        } while (number > 0);

        return idBuilder.reverse().toString();
    }
}

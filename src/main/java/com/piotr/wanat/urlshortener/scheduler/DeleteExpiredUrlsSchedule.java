package com.piotr.wanat.urlshortener.scheduler;

import com.piotr.wanat.urlshortener.db.repository.UrlMappingsRepository;
import com.piotr.wanat.urlshortener.configuration.properties.ScheduleProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class DeleteExpiredUrlsSchedule {

    @Autowired
    ScheduleProperties scheduleProperties;

    @Autowired
    private UrlMappingsRepository urlMappingsRepository;

    @Scheduled(fixedRateString = "#{@scheduleProperties.expireSchedule}")
    @Transactional
    public void deleteExpired() {
        log.info("----- DELETING EXPIRED URLS -----");
        int rowsDeleted = urlMappingsRepository.deleteExpired();
        log.info("----- DELETED [{}] ROWS -----", rowsDeleted);
    }
}

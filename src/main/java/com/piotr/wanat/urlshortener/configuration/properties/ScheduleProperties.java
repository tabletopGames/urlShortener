package com.piotr.wanat.urlshortener.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "urlshorten.schedule")
public class ScheduleProperties {

    private Long expireSchedule;

}
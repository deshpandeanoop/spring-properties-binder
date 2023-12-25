package com.explore.properties.binder.data;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ToString
public class AppProperties {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;
}
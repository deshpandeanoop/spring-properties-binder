package com.explore.properties.binder.data;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:service.properties")
@ToString
public class ServiceProperties {

    @Value("${service.name}")
    private String serviceName;

    @Value("${service.version}")
    private String serviceVersion;
}

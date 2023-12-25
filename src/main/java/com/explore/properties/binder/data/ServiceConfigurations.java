package com.explore.properties.binder.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "svc")
@Data
public class ServiceConfigurations {
    private List<ServiceConfig> configurations;
}
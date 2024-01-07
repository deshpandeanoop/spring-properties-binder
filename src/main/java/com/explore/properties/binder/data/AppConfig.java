package com.explore.properties.binder.data;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@ToString
@Data
public class AppConfig {
    private String name;
    private Integer responseTimeout;
    private Integer connectionTimeout;
}

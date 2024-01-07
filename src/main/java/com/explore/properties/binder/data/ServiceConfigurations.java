package com.explore.properties.binder.data;

import lombok.Data;
import java.util.List;

@Data
public class ServiceConfigurations {
    private List<ServiceConfig> configurations;
}
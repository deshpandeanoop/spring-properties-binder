package com.explore.properties.binder.data;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ServiceConfig {
    private String name;
    private String hostUri;
    private String isHttpsEnabled;
    private String isAuthEnabled;
    private List<Rule> serviceRules;
    private List<Rule> networkRules;
    private List<Rule> customRules;
    private Map<String, Object> metadata;
}

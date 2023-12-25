package com.explore.properties.binder.data;

import lombok.Data;

import java.util.List;

@Data
public class ServiceConfig {
    private String name;
    private String hostUri;
    private String isHttpsEnabled;
    private String isAuthEnabled;
    private List<String> owners;
}

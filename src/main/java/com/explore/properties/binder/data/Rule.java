package com.explore.properties.binder.data;

import lombok.Data;

import java.util.Map;

@Data
public class Rule {
    private String name;
    public Map<String, Object> rules;
}

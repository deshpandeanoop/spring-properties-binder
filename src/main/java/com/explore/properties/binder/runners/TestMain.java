package com.explore.properties.binder.runners;

import com.explore.properties.binder.data.Rule;
import com.explore.properties.binder.data.ServiceConfig;
import com.explore.properties.binder.data.ServiceConfigurations;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author anoopdeshpande
 */
public class TestMain {

    public static void main(String[] args) throws IOException {
        generateYaml(createServiceConfigs());
        System.out.println("Service configuration Generated!");
    }

    private static void generateYaml(final List<ServiceConfig> serviceConfigs) throws IOException {
        ServiceConfigurations serviceConfigurations = new ServiceConfigurations();
        serviceConfigurations.setConfigurations(serviceConfigs);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        Yaml yaml = new Yaml(dumperOptions);
        Map<String, Object> configurationMap = Collections.singletonMap("configurations", serializeToMap(serviceConfigs));
        Map<String, Object> svcMap = Collections.singletonMap("svc", configurationMap);
        StringWriter stringWriter = new StringWriter();
        yaml.dump(svcMap, stringWriter);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("/Applications/workspaces/personal-workspace/spring-properties-binder/src/main/resources/application.yml"))) {
            bufferedWriter.write(stringWriter.toString());
            bufferedWriter.flush();
        }
    }

    private static List<Map<String, Object>> serializeToMap(final List<ServiceConfig> serviceConfigs) {
        return serviceConfigs.stream().map(TestMain::serializeToMap).collect(Collectors.toList());
    }

    private static Map<String, Object> serializeToMap(final ServiceConfig serviceConfig) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", serviceConfig.getName());
        map.put("hostUri", serviceConfig.getHostUri());
        map.put("isHttpsEnabled", serviceConfig.getIsHttpsEnabled());
        map.put("isAuthEnabled", serviceConfig.getIsAuthEnabled());
        map.put("serviceRules", serializeToRMap(serviceConfig.getServiceRules()));
        map.put("networkRules", serializeToRMap(serviceConfig.getNetworkRules()));
        map.put("customRules", serializeToRMap(serviceConfig.getCustomRules()));
        map.put("metadata", serviceConfig.getMetadata());
        return map;
    }

    private static List<Map<String, Object>> serializeToRMap(final List<Rule> rules) {
        List<Map<String, Object>> res = new ArrayList<>();
        rules.forEach(rule -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name", rule.getName());
            map.put("rules", rule.rules);
            res.add(map);
        });
        return res;
    }

    private static List<ServiceConfig> createServiceConfigs() {
        List<ServiceConfig> serviceConfigs = new ArrayList<>();
        IntStream.rangeClosed(1, 1000).forEach(i -> serviceConfigs.add(createServiceConfig(i)));
        return serviceConfigs;
    }

    private static ServiceConfig createServiceConfig(final int index) {
        ServiceConfig serviceConfig = new ServiceConfig();
        serviceConfig.setName(String.format("service-%d", index));
        serviceConfig.setHostUri(String.format("https://%s.host.com", index));
        serviceConfig.setIsAuthEnabled("true");
        serviceConfig.setIsHttpsEnabled("true");
        serviceConfig.setServiceRules(createRules("svc-rule"));
        serviceConfig.setNetworkRules(createRules("nw-rule"));
        serviceConfig.setCustomRules(createRules("cus-rule"));
        serviceConfig.setMetadata(createConfigMap("metadata-prop"));
        return serviceConfig;
    }

    private static List<Rule> createRules(final String ruleNamePrefix) {
        List<Rule> rules = new ArrayList<>();
        IntStream.rangeClosed(1, 5).forEach(i -> {
            Rule rule = new Rule();
            rule.setName(String.format("%s-%d", ruleNamePrefix, i));
            Map<String, Object> map = new LinkedHashMap<>();
            IntStream.rangeClosed(1, 5).forEach(j -> {
                map.put("prop" + j, "value -" + j);
            });
            rule.setRules(map);
            rules.add(rule);
        });
        return rules;
    }

    private static Map<String, Object> createConfigMap(final String propNamePrefix) {
        Map<String, Object> configMap = new LinkedHashMap<>();
        IntStream.rangeClosed(1, 200).forEach(i -> configMap.put(String.format("%s-%d", propNamePrefix, i), String.format(UUID.randomUUID().toString())));
        return configMap;
    }
}

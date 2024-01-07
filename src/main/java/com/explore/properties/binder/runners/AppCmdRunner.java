package com.explore.properties.binder.runners;

import com.explore.properties.binder.data.ServiceConfigurations;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Slf4j
public class AppCmdRunner implements CommandLineRunner {
    @Autowired private ApplicationContext applicationContext;
    private Yaml yamlReader;
    private Yaml yaml;
    private Yaml yamlWriter;

    public AppCmdRunner() {
        System.setProperty("yaml.constructor.maxEventNumber", "10000"); // Adjust the limit accordingly
        LoaderOptions loaderOptions = new LoaderOptions();
        loaderOptions.setCodePointLimit(20 * 1024 * 1024);
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        yamlWriter = new Yaml(dumperOptions);
        yaml = new Yaml(loaderOptions);
        yamlReader = new Yaml(new Constructor(ServiceConfigurations.class), new Representer(), dumperOptions, loaderOptions);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Loading configurations..");
        long startTime = System.currentTimeMillis();
        if(Boolean.getBoolean(System.getProperty("loadUsingSpring"))) {
            ServiceConfigurations serviceConfigurations = applicationContext.getBean(ServiceConfigurations.class);
        } else {
            loadUsingYamlParser();
        }
        long timeTaken = (System.currentTimeMillis() - startTime)/1000;
        System.out.printf("Configurations loaded in %d seconds %n", timeTaken);
    }

    private void loadUsingYamlParser() throws IOException {
        String content = Files.readString(Paths.get("/Applications/workspaces/personal-workspace/spring-properties-binder/src/main/resources/application.yml"));
        Map<String, Object> parseMap = yaml.load(content);
        parseMap = (LinkedHashMap<String, Object>) parseMap.get("svc");
        StringWriter stringWriter = new StringWriter();
        yamlWriter.dump(parseMap, stringWriter);
        ServiceConfigurations serviceConfigurations = yamlReader.load(stringWriter.toString());
    }
}

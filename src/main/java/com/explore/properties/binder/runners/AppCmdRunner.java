package com.explore.properties.binder.runners;

import com.explore.properties.binder.data.AppConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppCmdRunner implements CommandLineRunner {
    @Autowired private AppConfig appConfig;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(appConfig);
    }
}

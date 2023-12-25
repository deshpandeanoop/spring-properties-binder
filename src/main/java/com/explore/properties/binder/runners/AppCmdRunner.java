package com.explore.properties.binder.runners;

import com.explore.properties.binder.data.AppProperties;
import com.explore.properties.binder.data.ServiceConfigurations;
import com.explore.properties.binder.data.ServiceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AppCmdRunner implements CommandLineRunner {
    @Autowired private AppProperties appProperties;
    @Autowired private ServiceProperties serviceProperties;
    @Autowired private ApplicationContext applicationContext;
    @Autowired private ServiceConfigurations serviceConfigurations;

    @Override
    public void run(String... args) throws Exception {
      log.info(appProperties.toString());
      log.info(serviceProperties.toString());
      log.info(String.valueOf(serviceConfigurations.getConfigurations().size()));
      ConfigurableEnvironment configurableEnvironment = (ConfigurableEnvironment) applicationContext.getEnvironment();
      MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
      log.info(String.valueOf(propertySources.size()));
    }
}

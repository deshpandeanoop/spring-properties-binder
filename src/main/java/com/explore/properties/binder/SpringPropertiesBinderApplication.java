package com.explore.properties.binder;

import com.explore.properties.binder.data.ServiceConfigurations;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class SpringPropertiesBinderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPropertiesBinderApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties(prefix = "svc")
//	@Scope("prototype")
//	public ServiceConfigurations serviceConfigurations() {
//		return new ServiceConfigurations();
//	}

}

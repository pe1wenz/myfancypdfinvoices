package com.peiwen.context;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peiwen.ApplicationLauncher;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackageClasses = ApplicationLauncher.class)
@PropertySource("classpath:/application.properties")
@PropertySource(value = "classpath:/application-${spring.profiles.active}.properties", ignoreResourceNotFound = true)
public class MyFancyPdfInvoicesApplicationConfiguration {


    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}

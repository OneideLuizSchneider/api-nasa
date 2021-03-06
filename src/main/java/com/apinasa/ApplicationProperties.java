package com.apinasa;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${nasa.api.url}")
    private String nasaApiUrl;

}

package com.major_project.ewallet.pocketbook.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Bean
    ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}

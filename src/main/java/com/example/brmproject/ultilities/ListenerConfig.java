package com.example.brmproject.ultilities;

import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ListenerConfig {
    @Bean
    public HttpSessionListener httpSessionListener() {
        return new SessionDestroyListener();
    }
}

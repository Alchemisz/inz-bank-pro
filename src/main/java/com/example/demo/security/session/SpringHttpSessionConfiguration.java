package com.example.demo.security.session;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

import java.util.concurrent.ConcurrentHashMap;

@EnableSpringHttpSession
@Configuration
public class SpringHttpSessionConfiguration {
    @Bean
    public SessionRepository sessionRepository() {
        MapSessionRepository sessionRepository = new MapSessionRepository(new ConcurrentHashMap<>());
        return sessionRepository;
    }
}

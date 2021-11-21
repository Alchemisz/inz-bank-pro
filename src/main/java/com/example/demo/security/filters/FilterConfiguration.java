package com.example.demo.security.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter());
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns("/client/*");
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}

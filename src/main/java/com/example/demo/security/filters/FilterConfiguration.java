package com.example.demo.security.filters;

import com.example.demo.user.UserRepository;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    private final UserRepository userRepository;

    public FilterConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter() {
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter(userRepository));
        registrationBean.addUrlPatterns("/api/*");
        registrationBean.addUrlPatterns("/client/*");
        registrationBean.addUrlPatterns("/admin/*");
        return registrationBean;
    }
}

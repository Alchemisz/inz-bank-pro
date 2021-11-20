package com.example.demo.security.filters;

import com.example.demo.entities.user.User;
import com.example.demo.entities.user.UserInfo;
import com.example.demo.entities.user.UserRepository;
import com.example.demo.security.HashingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
@Order(1)
public class AuthenticationFilter implements Filter {

    UserRepository userRepository;

    HashingService hashingService;

    @Autowired
    UserInfo userInfo;

    public AuthenticationFilter(UserRepository userRepository, HashingService hashingService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        String auth = httpServletRequest.getHeader("Authentication");
        if(auth != null) {
            String[] parts = auth.split(":");
            if(parts.length == 2) {
                userRepository.getUser(parts[0]).ifPresent(user -> {
                    String pass = parts[1];
                    String userHash = user.getPass();
                    if(hashingService.verify(userHash, pass)) {
                        userInfo.setUserPriviledges(user.getUserPriviledges());
                    }
                });

            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

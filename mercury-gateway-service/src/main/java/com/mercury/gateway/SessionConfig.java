package com.mercury.gateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.session.HeaderWebSessionIdResolver;
import org.springframework.web.server.session.WebSessionIdResolver;

/**
 * @author liuzhengyu
 * @version 1.0
 * @date 2021/5/18 01:22
 **/
@Configuration
public class SessionConfig {
    @Bean
    public WebSessionIdResolver headerWebSessionIdResolver() {
        HeaderWebSessionIdResolver resolver = new HeaderWebSessionIdResolver();
        resolver.setHeaderName("X-Auth-Token");

        return resolver;
    }
}

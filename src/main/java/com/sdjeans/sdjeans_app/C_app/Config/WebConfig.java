package com.sdjeans.sdjeans_app.C_app.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sdjeans.sdjeans_app.C_app.Interceptor.LongPollingInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    @Bean
    public LongPollingInterceptor longPollingInterceptor() {
        return new LongPollingInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(longPollingInterceptor());
    }
}

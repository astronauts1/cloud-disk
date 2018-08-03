package com.icourt.clouddisk.config;

import com.icourt.clouddisk.filter.TokenVerifyInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * token的filter配置类
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
@Configuration
public class TokenConfigure implements WebMvcConfigurer {




    @Bean
    public TokenVerifyInterceptor tokenVerifyInterceptor(){
        return new TokenVerifyInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenVerifyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/swagger-resources/**",
                        "/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**",
                        "/error",
                        "/v2/api-docs/**",
                        "/login");
    }
}

package com.icourt.clouddisk;

import com.icourt.clouddisk.filter.TokenVerifyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * token的filter配置类
 *
 * @author icourt
 * @create 2018 - 08 - 01 - TIME
 */
@Configuration
public class TokenConfigure {

    @Bean
    public FilterRegistrationBean<TokenVerifyFilter> myFilter(){
        FilterRegistrationBean<TokenVerifyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.addUrlPatterns("/api/v1/user/*");
        filterRegistrationBean.setFilter(new TokenVerifyFilter());
        return filterRegistrationBean;
    }

}

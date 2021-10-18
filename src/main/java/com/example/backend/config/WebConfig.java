package com.example.backend.config;

import com.example.backend.web.argumentresolver.LoginArgumentResolver;
import com.example.backend.web.filter.LogFilter;
import com.example.backend.web.filter.LoginCheckFilter;
import com.example.backend.web.interceptor.LoginCheckInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginIntercepter())
//                .order(1)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/css/**", "/*.ico", "/error");
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "/logout",
                       "/css/**", "/*.ico");
    }

//    @Bean
    public FilterRegistrationBean<Filter> logFilter(){
        FilterRegistrationBean<Filter> filterFilterRegistration = new FilterRegistrationBean<>();
        filterFilterRegistration.setFilter(new LogFilter());
        filterFilterRegistration.setOrder(1);
        filterFilterRegistration.addUrlPatterns("/*");
        filterFilterRegistration.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);
        return filterFilterRegistration;
    }

//    @Bean
    public FilterRegistrationBean<Filter> loginCheckFilter (){
        FilterRegistrationBean<Filter> filterFilterRegistration = new FilterRegistrationBean<>();
        filterFilterRegistration.setFilter(new LoginCheckFilter());
        filterFilterRegistration.setOrder(2);
        filterFilterRegistration.addUrlPatterns("/*");
        return filterFilterRegistration;
    }
}

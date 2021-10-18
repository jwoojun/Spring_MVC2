package com.example.backend.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("DoFilter filter init");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURL = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        try{
            log.info("REQUEST [{}] [{}] [{}]", uuid, request.getDispatcherType(),requestURL);
            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        }finally{
            log.info("RESPONSE[{} {}]", uuid, requestURL);
        }
    }

    @Override
    public void destroy() {
        log.info("Destroy filter init");

    }
}

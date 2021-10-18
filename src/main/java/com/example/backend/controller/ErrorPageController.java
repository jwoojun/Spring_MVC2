package com.example.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@Slf4j
public class ErrorPageController {
    static Map<String, String> errorInfo_repository = new HashMap<>();
    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        printErrorInfo(request);
        return "error-page/404";
    }

    /**
     *  RequestDispatch
     * */

    public static final String ERROR_EXCEPTION = "javax.servlet.error.exception";
    public static final String ERROR_EXCEPTION_TYPE = "javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME = "javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";

    private void printErrorInfo(HttpServletRequest request){
        initInfo();
//        errorInfo_repository.entrySet()
//                .forEach(key->log.info("{}= {}", key, errorInfo_repository.get(key)));
        errorInfo_repository.entrySet()
                .forEach(key->log.info("{}= {}", key, request.getAttribute(errorInfo_repository.get(key))));
        log.info("DispatchType= {}", request.getDispatcherType());
    }

    private static void initInfo(){
        errorInfo_repository.put("ERROR_EXCEPTION", "javax.servlet.error.exception");
        errorInfo_repository.put("ERROR_EXCEPTION_TYPE", "javax.servlet.error.exception_type");
        errorInfo_repository.put("ERROR_MESSAGE", "javax.servlet.error.message");
        errorInfo_repository.put("ERROR_REQUEST_URI", "javax.servlet.error.request_uri");
        errorInfo_repository.put("ERROR_SERVLET_NAME", "javax.servlet.error.servlet_name");
        errorInfo_repository.put("ERROR_STATUS_CODE", "javax.servlet.error.status_code");
    }
}

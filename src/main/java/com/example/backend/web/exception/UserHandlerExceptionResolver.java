package com.example.backend.web.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        try{
            if(ex instanceof UserException){
                log.info("UseerException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                if("application/json".equals(acceptHeader)){
                    Map<String, Object> reslt = new HashMap<>();
                    reslt.put("ex", ex.getClass());
                    reslt.put("message", ex.getMessage());
                    String res = objectMapper.writeValueAsString(reslt);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(res);
                    return new ModelAndView();
                }
            }else {
                return new ModelAndView("error/500");
            }
        }catch (IOException e){
            log.error("resolver ex", e);
        }
        return null;
    }
}

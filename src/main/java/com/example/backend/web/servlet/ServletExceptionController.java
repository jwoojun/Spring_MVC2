package com.example.backend.web.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class ServletExceptionController {
    @GetMapping("/error-ex")
    public void errorExample(){
        throw new RuntimeException("예외 발생");
    }

    /**
     * 상태를 지정하는 것이 가능하다.
     * */
    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        response.sendError(404, "404");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        response.sendError(500, "500");
    }
}

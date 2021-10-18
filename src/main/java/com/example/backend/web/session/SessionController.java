package com.example.backend.web.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Slf4j
@RestController
public class SessionController {
    @GetMapping("/session-info")
    public String sessionInfo(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session == null){
            return "세션이 존재하지 않습니다..";
        }
        session.getAttributeNames().asIterator()
                .forEachRemaining(name->log.info("session name={}, value={}", name, session.getAttribute(name)));
        log.info("sessionId={}", session.getId());
        log.info("getMaxInactiverInterval={}", session.getMaxInactiveInterval());
        log.info("creationTime={}", new Date(session.getCreationTime()));
        log.info("lastAccessedTime={}", new Date(session.getLastAccessedTime()));
        log.info("isName={}", session.isNew());
        return "세션이 존재합니다.";
    }

}
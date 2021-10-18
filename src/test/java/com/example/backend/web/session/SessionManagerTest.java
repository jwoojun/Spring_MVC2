package com.example.backend.web.session;

import com.example.backend.domain.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.assertj.core.api.Assertions.assertThat;


class SessionManagerTest {

    SessionManager manager = new SessionManager();

    @Test
    void 세션테스트(){
        // 세션 생성
        MockHttpServletResponse respone = new MockHttpServletResponse();
        Member member = new Member();
        manager.createSession(member, respone);

        // 요청에 응답 쿠키 저장 -> 웹
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setCookies(respone.getCookies());

        // 세션 조회
        Object result = manager.getSession(request);
        assertThat(request).isEqualTo(member);

//        manager.expireCookie(request);
//        Object expires = manager.getSession(request);
//        assertThat(expires).isNull();
    }

}
package com.example.thymeleaf.web;

import com.example.thymeleaf.domain.member.Member;
import com.example.thymeleaf.domain.member.MemberRepository;
import com.example.thymeleaf.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {
    private final MemberRepository repository;
    private final SessionManager manager;

    @GetMapping("/homeLogin_v1")
    public String homeLogin_v1(HttpServletRequest request, Model model){
        // 세션 관리자저장된 정보 조회
        Member member = (Member) manager.getSession(request);
        if(member == null){
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

    @GetMapping("/homeLogin_v2")
    public String homeLogin_v2(@CookieValue(name="memberId", required=false) Long memberId, Model model){
        if(memberId == null){
            return "home";
        }
        Member loginMember = repository.findById(memberId);
        if(loginMember == null){
            return "home";
        }
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/homeLogin_v3")
    public String homeLogin_v3(HttpServletRequest request, Model model){
        HttpSession session = request.getSession(false);
        if(session == null){
            return "home";
        }
        Member member = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if(member == null){
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }

    /**
     *  스프링이 제공하는 기능 -> 세션을 생성하지는 않는다.
     * */
    @GetMapping("/")
    public String homeLogin_v4(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)
                                       Member member, Model model){
        if(member == null){
            return "home";
        }
        model.addAttribute("member", member);
        return "loginHome";
    }
}

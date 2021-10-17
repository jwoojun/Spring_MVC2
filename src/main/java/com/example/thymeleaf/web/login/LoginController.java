package com.example.thymeleaf.web.login;

import com.example.thymeleaf.domain.login.LoginService;
import com.example.thymeleaf.domain.member.Member;
import com.example.thymeleaf.web.SessionConst;
import com.example.thymeleaf.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    private final LoginService service;
    private final SessionManager manager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form ){
        return "login/loginForm";
    }

    @PostMapping("/login")
    public String login_v1(@Valid @ModelAttribute LoginForm form, BindingResult result,
                           HttpServletResponse response){
        if(result.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = service.login(form.getLoginId(), form.getPassword());
        if(loginMember == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }
        Cookie cookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(cookie);
        return "redirect:/";
    }

    @PostMapping("/logout_v1")
    public String logout_v1(HttpServletResponse response){
        expireCookie(response, "memberId");
        return "redirect:/";
    }

    @PostMapping("/login_v2")
    public String login_v2(@Valid @ModelAttribute LoginForm form, BindingResult result,
                           HttpServletResponse response){
        if(result.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = service.login(form.getLoginId(), form.getPassword());
        if(loginMember == null){
            result.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "login/loginForm";
        }
        manager.createSession(loginMember, response);
        return "redirect:/";
    }

    @PostMapping("/logout_v2")
    public String logout(HttpServletRequest request){
        manager.expireCookie(request);
        return "redirect:/";
    }

//    @PostMapping("/login")
    public String login_v3(@Valid @ModelAttribute LoginForm form, BindingResult result,
                           HttpServletRequest request){
        if(result.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = service.login(form.getLoginId(), form.getPassword());
        if(loginMember == null){
            result.reject("loginFail", "잘못된 아이디 또는 비밀번호입니다. 올바른 정보를 입력해주세요.");
            return "login/loginForm";
        }
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout_v3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/";
    }

    public void expireCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
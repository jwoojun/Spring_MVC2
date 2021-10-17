package com.example.thymeleaf.domain.login;

import com.example.thymeleaf.domain.member.Member;
import com.example.thymeleaf.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberRepository repository;
    public Member login(String loginId, String password){
        return repository.findByLoginId(loginId)
                .filter(x->x.getPassword().equals(password))
                .orElse(null) ;
    }
}
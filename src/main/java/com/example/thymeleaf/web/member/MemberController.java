package com.example.thymeleaf.web.member;

import com.example.thymeleaf.domain.member.Member;
import com.example.thymeleaf.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberRepository repository;

    @GetMapping("/add")
    public String addForm(@ModelAttribute("member") Member member){
        return "members/addMemberForm";
    }


    @PostMapping("/add")
    public String save(@Valid @ModelAttribute Member member, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "members/addMemberForm";
        }
        repository.save(member);
        return "redirect:/";
    }


}

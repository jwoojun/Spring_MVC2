package com.example.backend.web.api;

import com.example.backend.web.exception.BadRequestException;
import com.example.backend.web.exception.UserException;
import com.example.backend.web.member.MemberDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ApiExceptionController {
    @GetMapping("/api/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        System.out.println("====");
        if(id.equals("user-ex")){
            throw new UserException();
        }
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad"))
            throw  new IllegalArgumentException("잘못된 입력 값");

        return new MemberDto(id, "hello"+id);
    }

    @GetMapping("/api/response-status-ex1")
    public String responseStatusEx1() {
        throw new BadRequestException();
    }

    @GetMapping("/api/response-status-ex2")
    public String responseStatusEx2() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }
}

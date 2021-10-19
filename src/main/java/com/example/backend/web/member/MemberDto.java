package com.example.backend.web.member;

import lombok.Data;

@Data
public class MemberDto {
    private String memberId;
    private String name;

    public MemberDto(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
    }
}

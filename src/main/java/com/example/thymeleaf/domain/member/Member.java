package com.example.thymeleaf.domain.member;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Data
public class Member {
    private Long id;

    @NotEmpty
    private String loginId;
    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    public Member(Long id, String loginId, String name, String password) {
        this.id = id;
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }

    public Member() {
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return Objects.equals(getId(), member.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}

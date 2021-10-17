package com.example.thymeleaf.config;

import com.example.thymeleaf.domain.item.ItemRepository;
import com.example.thymeleaf.domain.member.Member;
import com.example.thymeleaf.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestDataInit {
    private final MemberRepository repository;
    private final ItemRepository itemRepository;
    @PostConstruct
    public void init() {
        repository.save(new Member(1L, "aaaa", "Alice", "aaaa"));
        repository.save(new Member(2L, "awsdff", "Brian", "awsdff"));
    }
}

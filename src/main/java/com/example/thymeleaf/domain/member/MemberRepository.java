package com.example.thymeleaf.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {
    private static Map<Long, Member> repository = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member){
        member.setId(++sequence);
        log.info("save member={}", member);
        repository.put(member.getId(), member);
        return member;
    }


    public Member findById(Long id){
        return repository.get(id);
    }

    public Optional<Member> findByLoginId(String loginId){
        return findAll().stream()
                .filter(m->m.getLoginId().equals(loginId))
                .findAny();
    }

    public List<Member> findAll(){
        return new ArrayList<>(repository.values());
    }

    public void clearStore(){
        repository.clear();
    }
}

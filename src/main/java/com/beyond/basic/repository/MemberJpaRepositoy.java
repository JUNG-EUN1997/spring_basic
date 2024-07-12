package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;

import java.util.List;

public class MemberJpaRepositoy implements MemberRepository{
    @Override
    public Member save(Member member) {
//        원래는 저장이 잘 되었는지 DB 재조회 후 해당 member 리턴해야 함.
//        return member;
        return null;
    }

    @Override
    public List<Member> findAll() {
        return List.of();
    }

    @Override
    public Member findById(Long id) {
        return null;
    }
}


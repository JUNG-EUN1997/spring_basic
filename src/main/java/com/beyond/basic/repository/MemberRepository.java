package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;

import java.util.List;

// 다형성 구현을 위해 미리 구현해놓은 인터페이스
public interface MemberRepository {
    Member save(Member member); //Member 객체는 저장 메소드를 사용해야한다!

    List<Member> findAll(); // List Member는 findAll을 사용해야한다!

    Member findById(Long id); // findById는
}

package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
/*
 * MemberRepository가 되기 위해서는 JpaRepository를 상속해야하고
 *       상속 시, Entity명과 entity의 PK타입을 명시해야한다.
 * MemberRepository는 JpaRepository를 상속함으로써 주요기능을 상속
 * JpaRepository가 인터페이스임에도 해당 기능을 상속해서 사용할 수 있는 이유는
 *       hiberante구현체가 미리 구현되어있기 때문
 * ((런타임시점))에 사용자가 인터페이스에 정의한 메서드를 프록시(대리인)객체가
 *       리플랙션 기술을 통해 메서드를 구현
 *           리플랙션 : 런타임시점에 기능들이 구현이 되었다!
 *           프록시 : 가짜 객체
 * */

// 다형성을 처리하지 않은 버전의 MemberSpringDataJpaRepository.java
public interface MyMemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByEmail(String email); //런타임 시점에 이거 만들어줘! 라고 말한 것
    List<Member> findByName(String name); //추가하려고 하는 새로운 메소드
}

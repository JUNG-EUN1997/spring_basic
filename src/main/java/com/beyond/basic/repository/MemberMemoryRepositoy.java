package com.beyond.basic.repository;

import com.beyond.basic.domain.member.Member;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

// 한 객체를 클래스 단위로 확장하여 <<<<🍀여러 클래스에서 호출🍀>>>>하여 쓸 수 있는
// (((((싱글톤)))) 활용하기

// 해당 클래스가 Repository계층임을 표현함과 동시에 싱글톤 객체로 생성한다.
//          그러면 new 키워드를 사용하면 아니된다!
@Repository
public class MemberMemoryRepositoy implements MemberRepository{
    private final List<Member> memberList;

    MemberMemoryRepositoy(){
        this.memberList = new ArrayList<>();
    }


    @Override
    public Member save(Member member) {
        memberList.add(member);
//        원래는 저장이 잘 되었는지 DB 재조회 후 해당 member 리턴해야 함.
//        return member;
        return null;
    }

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }
}

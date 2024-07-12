package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.member.Member;
import com.beyond.basic.domain.member.MemberReqDto;
import com.beyond.basic.domain.member.MemberResDto;
import com.beyond.basic.repository.MemberJdbcRepositoy;
import com.beyond.basic.repository.MemberMemoryRepositoy;
import com.beyond.basic.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//    input값의 검증 및 실질적인 비지니스 로직은 서비스 계층에서 수행

@Service // 서비스 계층임을 표현함과 동시에 ⭐싱글톤 객체로 생성⭐
public class MemberService {
    private final MemberRepository memberRepository; // membercontroller에서 받은 객체생성
//    객체변수로 생성
//    최초 한번 객체 생성 후 만들어지지 못하도록 final 추가

//     MemberService 생성자가 호출될 때 객체가 생성되도록 변경
    @Autowired // ⭐ 싱글톤 객체 주입(DI / 디펜더시 인젝션) : 받는다는 것을 의미
    public MemberService(MemberJdbcRepositoy memoryRepositoy){ // 어떤 싱글톤 객체를 주입받는지 작성
        this.memberRepository = memoryRepositoy; // new MemberMemoryRepositoy() 를 싱글톤 객체로 만든 것
    }


//    @Autowired
//    private MemberController memberController;


    public void memberCreate(MemberReqDto dto){
        if(dto.getPassword().length() < 8){
            throw  new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }

        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
//        Service.java에서 ReqDto객체를 실제 DB 저장용 객체로 조립해야한다. >> 보통 Service 레이어에서 하는 것이 일반적이다.
//              그렇기때문에 Service의 매개변수는 ReqDto여야한다.

        memberRepository.save(member); // DB에다 저장을 하겠따!
    }

    public Member memberDetail(Long id){
        return memberRepository.findById(id);
    }

    public List<MemberResDto> memberList(){ // list를 resdto로 변경
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();

        for (Member member : memberList) {
            MemberResDto dto = new MemberResDto();
            dto.setName(member.getName());
            dto.setEmail(member.getEmail());
            memberResDtos.add(dto);
        }

        return memberResDtos;
    }
}

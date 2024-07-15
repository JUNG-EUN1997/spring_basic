package com.beyond.basic.service;

import com.beyond.basic.controller.MemberController;
import com.beyond.basic.domain.member.Member;
import com.beyond.basic.domain.member.MemberDetailResDto;
import com.beyond.basic.domain.member.MemberReqDto;
import com.beyond.basic.domain.member.MemberResDto;
import com.beyond.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//    input값의 검증 및 실질적인 비지니스 로직은 서비스 계층에서 수행

@Service // 서비스 계층임을 표현함과 동시에 ⭐싱글톤 객체로 생성⭐

// @Transactional어노테이션을 통해 ((각각의 모든 메서드))에 트랜잭션을 적용
//         각 메서드 마다 하나의 트랜잭션으로 묶는다는 뜻. 하나의 메서드라도 실패하면 롤백
// 만약 예외 발생 시, 롤백처리 자동화
// 트랜잭션은 따라다니면서 상태처리를 해야하기 때문에 클래스에 붙여 전체의 메소드에 적용하는 것은 권장하지 않는다.
@Transactional
public class MemberService {
    private final MyMemberRepository memberRepository; // membercontroller에서 받은 객체생성
//    객체변수로 생성
//    최초 한번 객체 생성 후 만들어지지 못하도록 final 추가

//     MemberService 생성자가 호출될 때 객체가 생성되도록 변경
    @Autowired // ⭐ 싱글톤 객체 주입(DI / 디펜더시 인젝션) : 받는다는 것을 의미
    public MemberService(MyMemberRepository memoryRepositoy){ // 어떤 싱글톤 객체를 주입받는지 작성
        this.memberRepository = memoryRepositoy; // new MemberMemoryRepositoy() 를 싱글톤 객체로 만든 것
    }


//    @Autowired
//    private MemberController memberController;


    public void memberCreate(MemberReqDto dto){
        if(dto.getPassword().length() < 8){
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }

        /* 방법 1. getter setter 활용\
        Member member = new Member();
        member.setName(dto.getName());
        member.setEmail(dto.getEmail());
        member.setPassword(dto.getPassword());
        * */
//        방법 2. dto 객체를 활용하여 member 객체 생성하기
        Member member = dto.toEntity();



//        Service.java에서 ReqDto객체를 실제 DB 저장용 객체로 조립해야한다. >> 보통 Service 레이어에서 하는 것이 일반적이다.
//              그렇기때문에 Service의 매개변수는 ReqDto여야한다.
        System.out.println(member);
        memberRepository.save(member); // DB에다 저장을 하겠따!
    }

    public MemberDetailResDto memberDetail(Long id){
        Optional<Member> optmember = memberRepository.findById(id);
        Member member = optmember.orElseThrow(() -> new EntityNotFoundException("없는 회원입니다."));
//        🍀 Optional을 사용한 목적
//              트랜잭션 롤백을 위한 예외 강제 발생!
//              클라이언트에게 적절한 예외 메세지와 상태코드 발생!
//              단, 트랜잭션을 하기위해 상단에 @Transactional 어노테이션이 붙어있어야 한다.

        MemberDetailResDto memberDetailResDto = member.detFromEntity();
        // 이 괄호 안에 매개변수로 member로 넣어주거나 아니거나 똑같은 것
        return memberDetailResDto;
    }

    public List<MemberResDto> memberList(){ // list를 resdto로 변경
        List<MemberResDto> memberResDtos = new ArrayList<>();
        List<Member> memberList = memberRepository.findAll();
        for (Member member : memberList) {
//            MemberResDto dto = new MemberResDto();
//            dto.setId(member.getId());
//            dto.setName(member.getName());
//            dto.setEmail(member.getEmail());
//            dto.setCreatedTime(member.getCreatedTime());
            MemberResDto dto = member.listFromEntity();
            memberResDtos.add(dto);
        }

        return memberResDtos;
    }
}

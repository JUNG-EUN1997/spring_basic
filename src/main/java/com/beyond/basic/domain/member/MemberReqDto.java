package com.beyond.basic.domain.member;

import lombok.Data;
import lombok.NoArgsConstructor;

// 사용자에게 요청(response)를 받을 때 사용하는 객체
@Data
@NoArgsConstructor
public class MemberReqDto {
    private Long id; //보안상의 문제는 없을 것
    private String name;
    private String email;
    private String password;

//    dto에서 entity로 변환

//    추후에는 빌더패턴으로 변환
//      빌더패턴이란? 🍀추후설명🍀
    public Member toEntity(){
//        만약에 getter setter로 쓸거면 매개변수로 객체를 받아줘야함
        Member member = new Member(this.name,this.email,this.password);

//        entity에서 dto로 변하는 메소드란?
//        controll -> db : to entity
//        controll <- db : from entity

        return member;
    }
}

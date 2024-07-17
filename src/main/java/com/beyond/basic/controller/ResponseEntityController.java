package com.beyond.basic.controller;

import com.beyond.basic.domain.member.Member;
import com.beyond.basic.domain.CommonResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response/entity")
public class ResponseEntityController {
//    ⭐ case 1 ⭐ @ResponseStatus 어노테이션 방식

    /*
    * 200 : 정상
    * 201 : created : 무엇이 만들어졌따 : 회원생성 등
    * 400대 : 사용자의 입력이 잘못 된 경우
    * 400 : 입력 값이 잘못 된 경우
    * 500대 : 서버가 잘못된 경우
    *
    * */
    @GetMapping("/annotation1")
    @ResponseStatus(HttpStatus.OK) // 코드를 200으로 조작 함
    public String annotation1(){
        return "ok";
    }

    @GetMapping("/annotation2")
    @ResponseStatus(HttpStatus.CREATED) // 코드를 201로 조작 함 : header에 값이 들어감
    public Member annotation2(){
//        (가정) 회원생성 후 db에 저장
        Member member = new Member("kkz", "zz@mail.com","111111");
        return member;
    }


//    ⭐ case 2 ⭐ 메서드 체이닝 방식 : ResponseEntity의 클래스메서드 사용
    @GetMapping("/chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("kkz", "zz@mail.com","111111");
        return ResponseEntity.ok(member); // header에 값이 들어감
    }

    @GetMapping("/chaining2")
    public ResponseEntity<Member> chaining2(){
        Member member = new Member("kkz", "zz@mail.com","111111");
        return ResponseEntity.status(HttpStatus.CREATED).body(member); // header에 값이 들어감
    }

    @GetMapping("/chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member("kkz", "zz@mail.com","111111");
        return ResponseEntity.notFound().build();
    }



//    🍀활용도 가장 높음!🍀
//    ⭐ case 3 ⭐ ResponseEntity 객체를 직적 Custom하여 생성하는 방식   ::
    @GetMapping("/custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member("kkz", "zz@mail.com","111111");
        return new ResponseEntity<>(member, HttpStatus.CREATED);
//        member객체를 한번 감싸주고, created도 넣어주기
    }

    @GetMapping("/custom2")
    public ResponseEntity<CommonResDto> custom2(){
        Member member = new Member("kkz", "zz@mail.com","111111");

        CommonResDto commonResDto = new CommonResDto(HttpStatus.CREATED, "member is successfully created", member);
        // body에 값을 주기위해 객체에 httpstatus를 추가함

//        body에 들어가는 값은 commonResDto 객체만 들어간다.
        return new ResponseEntity<>(commonResDto, HttpStatus.CREATED);
    }


}



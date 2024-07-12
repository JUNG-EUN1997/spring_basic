package com.beyond.basic.domain.member;

import lombok.Data;

import javax.persistence.*;
//import javax.persistence.Id;

@Data
/*
* @Entity 어노테이션을 통해 엔티티매니저에게 객체관리를 위임
* 해당 클래스명으로 테이블 및 컬럼을 자동생성하고 각종 설정정보 위임
* */
@Entity // JPA에게 엔티티매니저가 관리해야할 요소다! 라고 설정해놓은 것.
// 실행 시, 연결해놓은 DB에 해당 테이블이 존재하지 않으면 엔티티매니저가 DB에 테이블을 생성해준다
public class Member {
    @Id //pk 설정
//    identity : auto_increment 설정
//    auto : jpa 자동으로 적절한 전략을 선택하도록 맡기는 것
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //long은 bigint로 변환
//    String은 Varchar(255)로 기본으로 변환, name변수명이 name컬럼명으로 변환
    private String name;

//    @Column을 통해 해당 컬럼의 추가 설정을 진행할 수 있다.
//    nullable = false  : notnull 제약조건
//    unique = true : unique 제약조건
    @Column(unique = true, length = 50)
    private String email;


//    @Column(name="pw") 실제 DB에 들어가는 name을 변경할 수 있다.
//    하지만 권장하지 않는다. DB의 컬럼명과 변수명을 일치시켜야 혼선을 줄일 수 있다.
    private String password;


//    camelCase를 사용하면, DB에는 _로 들어간다
//    ex) JAVA에는 passWord 일 때 DB에는 pass_word 로 들어간다.
}

package com.beyond.basic.domain.member;

import lombok.Data;

// 사용자에게 요청(response)를 받을 때 사용하는 객체
@Data
public class MemberReqDto {
    private Long id; //보안상의 문제는 없을 것
    private String name;
    private String email;
    private String password;
}

package com.beyond.basic.domain.member;

// dto세팅, model 세팅, data세팅,

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MemberDetailResDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String createdTime;
}

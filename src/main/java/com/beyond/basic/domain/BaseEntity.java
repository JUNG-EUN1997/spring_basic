package com.beyond.basic.domain;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
// 기본적으로 Entity는 상속관계가 불가능, 해당 어노테이션을 붙여야 상속관계 성립 가능


// 모든 객체에서 동시에 사용할 base 객체
public abstract class BaseEntity { // 단독으로 사용하지 못 하도록 abstract 선언
//        camelCase를 사용하면, DB에는 _(언더바)로 들어간다
//    ex) JAVA에는 createdTime 일 때 DB에는 created_time 로 들어간다.
    @CreationTimestamp //DB에는 current_timestamp가 생성되지 않음.
    private LocalDateTime createdTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}

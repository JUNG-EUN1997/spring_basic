package com.beyond.basic.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class CommonResDto {
    private int status_code; // camelCase로 작성하면 자동으로 언더바로 구성 된다!
    private String status_message;
    private Object result; // 모든 객체를 받을 수 있게 Object로 선언

    public CommonResDto(HttpStatus httpStatus, String message, Object result) {
        this.status_code = httpStatus.value(); // 숫자 code로 변경
        this.status_message = message;
        this.result = result;
    }
}
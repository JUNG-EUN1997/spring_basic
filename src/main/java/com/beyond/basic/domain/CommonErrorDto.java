package com.beyond.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonErrorDto {
    private int status_code; // camelCase로 작성하면 자동으로 언더바로 구성 된다!
    private String error_message;

//    public CommonErrorDto(HttpStatus httpStatus) {
//        this.status_code = httpStatus.value(); // 숫자 code로 변경
//    }
}
package com.beyond.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // Bean을 쓰려면 Configuration을 써야함!
@EnableSwagger2
public class SwaggerConfig {
    @Bean //메소드 단위로 싱글톤 객체 생성!
//    Docket : Swagger 구성의 핵심 기능 클래스
//    Docket을 리턴함으로써 싱글톤 객체로 생성
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select() // 어떤 컨트롤러 또는 어떤 api를 swagger 문서에 포함 시킬지 선택

//                모든 RequestHandlerSelectors를 문서화 대상으로 선택한다는 설정
                .apis(RequestHandlerSelectors.any())

//                특정 paths만 문서화 대상으로 하겠다 라는 설정
//                /rest ... 로만! 시작하는 것으로 대상하겠다!
                .paths(PathSelectors.ant("/rest/**")) // * 1개면, 직계. **2개면 자손까지 포함

//                buildup 패턴이라는 뜻
                .build();
    }
}

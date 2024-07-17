package com.beyond.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
// 내부에 ComponentScan이 명시되어 있음
//		동등하거나 하위레벨에 있는 @Component들을 Scan해서(=>ComponentScanning 수행) 싱글톤 객체로 만들어주는 것을 함
// 따라서, 상위에 폴더를 만들면 작동이 안되는 것 이었음

// 주로 web 서블릿 기반의 구성요소를 스캔하고 자동으로 등록하는 기능
// 예를들어 webservlet webfliter, weblistener 등
@ServletComponentScan
public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}

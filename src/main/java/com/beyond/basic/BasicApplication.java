package com.beyond.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// 내부에 ComponentScan이 명시되어 있음
//		동등하거나 하위레벨에 있는 @Component들을 Scan해서(=>ComponentScanning 수행) 싱글톤 객체로 만들어주는 것을 함
// 따라서, 상위에 폴더를 만들면 작동이 안되는 것 이었음

public class BasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicApplication.class, args);
	}

}

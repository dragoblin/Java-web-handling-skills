package com.myboot01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 스프링 부트 애플리케이션으로 설정
@SpringBootApplication
public class Myboot01Application {
	
	// 스프링 부트 프로젝트는 반드시 main() 메서드가 있어야 함
	public static void main(String[] args) {
		SpringApplication.run(Myboot01Application.class, args);
	}

}

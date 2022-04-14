package com.myboot02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
	@ResponseBody
	@RequestMapping("/")	// 모든 요청을 처리
	public String home() {
		System.out.println("Hello Boot!!");
		return "Hello Boot!!";	// 브라우저로 출력
	}
	
	// message 속성에 문자열을 저장한 후 hello.html로 전달
	@RequestMapping("/hello.do")
	public String hello(Model model) {
		System.out.println("안녕하세요");
		model.addAttribute("message", "hello.jsp입니다.!");
		return "hello";
	}
	
}
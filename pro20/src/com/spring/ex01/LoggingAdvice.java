package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// 인터페이스 MethodInterceptor를 구현해 어드바이스 클래스를 만듦
public class LoggingAdvice implements MethodInterceptor {
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		// 메서드 호출 전에 수행하는 구문
		System.out.println("[메서드 호출 전 : LogginAdvice");
		System.out.println(invocation.getMethod() + "메서드 호출 전");

		Object object = invocation.proceed();
		
		// 메서드 호출 후에 수행하는 구문
		System.out.println("[메서드 호출 후 : loggingAdvice");
		System.out.println(invocation.getMethod() + "메서드 호출 후");
		
		return object;
	}
}
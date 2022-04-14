package com.spring.ex01;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// �������̽� MethodInterceptor�� ������ �����̽� Ŭ������ ����
public class LoggingAdvice implements MethodInterceptor {
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		// �޼��� ȣ�� ���� �����ϴ� ����
		System.out.println("[�޼��� ȣ�� �� : LogginAdvice");
		System.out.println(invocation.getMethod() + "�޼��� ȣ�� ��");

		Object object = invocation.proceed();
		
		// �޼��� ȣ�� �Ŀ� �����ϴ� ����
		System.out.println("[�޼��� ȣ�� �� : loggingAdvice");
		System.out.println(invocation.getMethod() + "�޼��� ȣ�� ��");
		
		return object;
	}
}
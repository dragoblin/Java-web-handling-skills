package com.spring.ex03;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class MemberTest1 {
	public static void main(String[] args) {
		
		// ���� �� member.xml�� ������ ��� ���� ������ �� ����
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("member.xml"));
		
		// id�� memberService�� ���� ������
		MemberService service = (MemberService) factory.getBean("memberService");
		
		service.listMembers();
	}
}
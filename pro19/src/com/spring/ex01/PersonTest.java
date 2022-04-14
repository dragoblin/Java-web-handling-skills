package com.spring.ex01;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class PersonTest {
	public static void main(String[] args) {
		
		// ���� �� person.xml�� �о� �鿩 ���� ����
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("person.xml"));
		
		// id�� personService�� ���� ������
		PersonService person = (PersonService) factory.getBean("personService");
		
		// �� �̻� �ڹ� �ڵ忡�� ��ü�� ���� �������� �ʾƵ� �ǹǷ� �ּ� ó��
		// PersonService person = new PersonServiceImpl();
		
		// ������ ���� �̿��� name ���� ���
		person.sayHello();
		
	}
}
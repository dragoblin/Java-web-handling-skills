package com.myspring.pro28.ex04;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailService {
	
	// mail-context.xml���� ������ ���� �ڵ����� ����
	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    @Async
    public void sendMail(String to, String subject, String body) {
    	
    	// MimeMessage Ÿ�� ��ü�� ����
    	MimeMessage message = mailSender.createMimeMessage();
    	
    	try {
    		
    		// ������ ������ ���� MimeMessageHelper ��ü�� ����
    		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    		
			messageHelper.setCc("���Ÿ����ּ�");
			
			// ���� ���� �� ������ �̸����� ǥ�õǰ� ��
			messageHelper.setFrom("���Ÿ����ּ�", "ȫ�浿");
			
			// ����, ����ó, ������ ������ ������ ����
			messageHelper.setSubject(subject);
			messageHelper.setTo(to); 
			
			// ���� ������ HTML�� ǥ�õǰ� �Ϸ��� �ݵ�� �� ��° ���� ���� true�� ����
			messageHelper.setText(body, true);
			
			mailSender.send(message);
			
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
 
	@Async
	// mail-context.xml���� �̸� ������ ���� �ּҷ� ���� ������ ����
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
	
}
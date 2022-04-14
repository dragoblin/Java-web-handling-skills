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
	
	// mail-context.xml에서 설정한 빈을 자동으로 주입
	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    private SimpleMailMessage preConfiguredMessage;
 
    @Async
    public void sendMail(String to, String subject, String body) {
    	
    	// MimeMessage 타입 객체를 생성
    	MimeMessage message = mailSender.createMimeMessage();
    	
    	try {
    		
    		// 메일을 보내기 위해 MimeMessageHelper 객체를 생성
    		MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
    		
			messageHelper.setCc("수신메일주소");
			
			// 메일 수신 시 지정한 이름으로 표시되게 함
			messageHelper.setFrom("수신메일주소", "홍길동");
			
			// 제목, 수신처, 내용을 설정해 메일을 보냄
			messageHelper.setSubject(subject);
			messageHelper.setTo(to); 
			
			// 메일 내용이 HTML로 표시되게 하려면 반드시 두 번째 인자 값을 true로 설정
			messageHelper.setText(body, true);
			
			mailSender.send(message);
			
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
	}
 
	@Async
	// mail-context.xml에서 미리 설정한 수신 주소로 메일 내용을 보냄
	public void sendPreConfiguredMail(String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage(preConfiguredMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}
	
}
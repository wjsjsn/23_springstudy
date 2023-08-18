package com.ict.email.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public void sendEmail(String randomNumber, String toMail) {
		MailHandler sendMail;
		try {
			sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[대한민국 국방부에서 재입대 관련으로 연락드립니다.]"); // 메일 제목
			
			// 내용
			sendMail.setText("" 
						+ "<table><tbody>"
						+ "<tr><td><h2>국방부 인증</h2></td></tr>"
						+ "<tr><td><h3>01065238205 이 번호로 인증번호를 보내주십시오.</h3></td></tr>"
						+ "<tr><td><font size='28px'>인증번호 안내</font></td></tr>"
						+ "<tr><td></td></tr>"
						+ "<tr><td>꼭 인증하셔서 본인확인 절차가 이루어지도록 협조 부탁드립니다.</td></tr>"
						+ "<tr><td><font size='28px'>확인번호 : " + randomNumber + "</font></td></tr>"
						+ "</tbody></table>");
			
			// 보내는 이
			sendMail.setFrom("zdbstjdgnsz@gmail.com", "대한민국 국방부");
			
			// 받는 이
			sendMail.setTo(toMail);
			sendMail.send();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
}

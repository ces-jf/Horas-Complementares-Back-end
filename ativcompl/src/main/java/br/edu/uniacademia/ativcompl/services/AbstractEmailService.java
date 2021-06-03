package br.edu.uniacademia.ativcompl.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender; 
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.edu.uniacademia.ativcompl.domain.Activity;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendOrderConfirmationEmail(Activity obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromActivity(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromActivity(Activity obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getStudent().getUser().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Atividade Concluida! Atividade: " + obj.getName());
		sm.setText(obj.toString());
		return sm;
	}
	
	protected String htmlFromTemplateActivity(Activity obj) {
		Context context = new Context();
		context.setVariable("activity", obj);
		return templateEngine.process("email/confirmationActivity", context);
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Activity obj) {
		try {
			MimeMessage mm = prepareMimeMessageFromActivity(obj);
			sendHtmlEmail(mm);
		}catch (MessagingException e) {
			sendOrderConfirmationEmail(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromActivity(Activity obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getStudent().getUser().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Atividade Concluida! Atividade: " + obj.getName());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplateActivity(obj), true);
		return mimeMessage;
	}

}

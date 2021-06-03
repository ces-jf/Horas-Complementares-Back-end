package br.edu.uniacademia.ativcompl.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.edu.uniacademia.ativcompl.domain.Activity;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
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

}

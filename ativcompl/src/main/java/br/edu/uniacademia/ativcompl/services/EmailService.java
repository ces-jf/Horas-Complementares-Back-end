package br.edu.uniacademia.ativcompl.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.domain.User;

public interface EmailService {

		void sendOrderConfirmationEmail(Activity obj);
		
		void sendEmail(SimpleMailMessage msg);
		
		void sendOrderConfirmationHtmlEmail(Activity obj);
		
		void sendHtmlEmail(MimeMessage msg);

		void sendNewPasswordEmail(User user, String newPass);

}

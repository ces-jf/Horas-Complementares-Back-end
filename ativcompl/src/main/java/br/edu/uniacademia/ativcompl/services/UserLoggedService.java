package br.edu.uniacademia.ativcompl.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.edu.uniacademia.ativcompl.security.UserSS;

public class UserLoggedService {

	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}

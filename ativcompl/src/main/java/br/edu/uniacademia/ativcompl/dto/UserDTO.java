package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;

import br.edu.uniacademia.ativcompl.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String registration;
	private String name;
	private String email;
				
	public UserDTO() {}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.registration = obj.getRegistration();
		this.name = obj.getName();
		this.email = obj.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

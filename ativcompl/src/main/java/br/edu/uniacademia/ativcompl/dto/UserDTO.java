package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.edu.uniacademia.ativcompl.domain.User;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotEmpty(message = "O campo não pode estar em branco")
	private String registration;
	
	@NotEmpty(message = "O campo não pode estar em branco")
	@Length(min = 3, max = 150, message = "O tamanho deve ser entre 3 e 150 caracteres")
	private String name;
	
	@NotEmpty(message = "O campo não pode estar em branco")
	@Email(message = "Email inválido")
	private String email;
	private String password;
	
	
	public UserDTO() {}
	
	public UserDTO(User obj) {
		this.id = obj.getId();
		this.registration = obj.getRegistration();
		this.name = obj.getName();
		this.email = obj.getEmail();
		this.password = obj.getPassword();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
		
}

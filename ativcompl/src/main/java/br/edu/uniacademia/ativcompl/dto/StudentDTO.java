package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.uniacademia.ativcompl.domain.Student;

public class StudentDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// Dados de student
		private Long id;
		@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
		@NotEmpty(message = "O campo não pode estar em branco")
		private Date startCourse;

		// Dados de user
		@NotEmpty(message = "O campo não pode estar em branco")
		private String registration;

		@NotEmpty(message = "O campo não pode estar em branco")
		@Length(min = 3, max = 150, message = "O tamanho deve ser entre 3 e 150 caracteres")
		private String name;

		@NotEmpty(message = "O campo não pode estar em branco")
		@Email(message = "Email inválido")
		private String email;

		@NotEmpty(message = "O campo não pode estar em branco")
		private String password;

		// Dados de address
		private String street;
		private String number;
		private String district;
		private String city;

		public StudentDTO() {
		}
		
		public StudentDTO(Student obj) {
			this.id = obj.getId();
			this.startCourse = obj.getStartCourse();
			this.registration = obj.getUser().getRegistration();
			this.name = obj.getUser().getName();
			this.email = obj.getUser().getEmail();
			this.password = obj.getUser().getPassword();
			this.street = obj.getAddress().getStreet();
			this.number = obj.getAddress().getNumber();
			this.district = obj.getAddress().getDistrict();
			this.city = obj.getAddress().getCity();
		}
		
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public Date getStartCourse() {
			return startCourse;
		}

		public void setStartCourse(Date startCourse) {
			this.startCourse = startCourse;
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

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

}

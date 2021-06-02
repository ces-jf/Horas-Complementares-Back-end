package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.uniacademia.ativcompl.services.validation.UserInsert;

@UserInsert
public class StudentNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	// Dados de student
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
	private Long userType_id;
	private Long campus_id;
	private Long course_id;

	// Dados de address
	private String street;
	private String number;
	private String district;
	private String city;

	public StudentNewDTO() {
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

	public Long getCampus_id() {
		return campus_id;
	}

	public void setCampus_id(Long campus_id) {
		this.campus_id = campus_id;
	}

	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public Date getStartCourse() {
		return startCourse;
	}

	public void setStartCourse(Date startCourse) {
		this.startCourse = startCourse;
	}

	public Long getUserType_id() {
		return userType_id;
	}

	public void setUserTyep_id(Long userType_id) {
		this.userType_id = userType_id;
	}

}

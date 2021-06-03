package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ActivityNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date start;
	
	private Double workload;
	private Double hoursCompleted;
	private Boolean closed;
	private String certificate;
	private Long student_id;
	private Long category_id;
	
	public ActivityNewDTO() {}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Double getWorkload() {
		return workload;
	}

	public void setWorkload(Double workload) {
		this.workload = workload;
	}

	public Double getHoursCompleted() {
		return hoursCompleted;
	}

	public void setHoursCompleted(Double hoursCompleted) {
		this.hoursCompleted = hoursCompleted;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}


	public Long getStudent_id() {
		return student_id;
	}


	public void setStudent_id(Long student_id) {
		this.student_id = student_id;
	}

	public Long getCategory_id() {
		return category_id;
	}


	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}	
}

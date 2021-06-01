package br.edu.uniacademia.ativcompl.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.uniacademia.ativcompl.domain.Activity;
import br.edu.uniacademia.ativcompl.domain.enums.Valuation;

public class ActivityDTO {
	
	private Long id;
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date start;
	
	private Double workload;
	private Double hoursCompleted;
	private Boolean closed;
	private String certificate;
	private Integer valuation;
	private String justification;
	
	public ActivityDTO() {}
	
	public ActivityDTO(Activity obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.start = obj.getStart();
		this.workload = obj.getWorkload();
		this.hoursCompleted = obj.getHoursCompleted();
		this.closed = obj.getClosed();
		this.certificate = obj.getCertificate();
		this.valuation = (obj.getValuation() == null) ? null : obj.getValuation().getCod();
		this.justification = obj.getJustification();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public Valuation getValuation() {
		return Valuation.toEnum(valuation);
	}

	public void setValuation(Valuation valuation) {
		this.valuation = valuation.getCod();
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
}

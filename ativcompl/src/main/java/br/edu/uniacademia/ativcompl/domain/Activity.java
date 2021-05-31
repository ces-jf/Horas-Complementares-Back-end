package br.edu.uniacademia.ativcompl.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.edu.uniacademia.ativcompl.domain.enums.Valuation;

@Entity
public class Activity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date start;
	
	private Double workload;
	private Double hoursCompleted;
	private Boolean closed;
	private String certificate;
	private Integer valuation;
	private String justification;
	

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
		
	public Activity() { }

	public Activity(Long id, String name, Date start, Double workload, Double hoursCompleted, Boolean closed,
			String certificate, Valuation valuation, String justification, Student student, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.start = start;
		this.workload = workload;
		this.hoursCompleted = hoursCompleted;
		this.closed = closed;
		this.certificate = certificate;
		this.valuation = (valuation == null) ? null : valuation.getCod();
		this.justification = justification;
		this.student = student;
		this.category = category;
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
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
		
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}

package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;

import br.edu.uniacademia.ativcompl.domain.Course;

public class CourseDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Double workload;
	
	public CourseDTO() {}
	
	public CourseDTO(Course obj) {
		this.id = obj.getId();
		this.name = obj.getName();
		this.workload = obj.getWorkload();
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

	public Double getWorkload() {
		return workload;
	}

	public void setWorkload(Double workload) {
		this.workload = workload;
	}
	
}

package br.edu.uniacademia.ativcompl.dto;

import java.io.Serializable;

import br.edu.uniacademia.ativcompl.domain.Campus;

public class CampusDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	
	public CampusDTO() {}
	
	public CampusDTO(Campus obj) {		
		this.id = obj.getId();
		this.name = obj.getName();
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
	
}

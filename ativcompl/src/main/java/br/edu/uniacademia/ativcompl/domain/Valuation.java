package br.edu.uniacademia.ativcompl.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.edu.uniacademia.ativcompl.domain.enums.ValuetionEnum;

@Entity
public class Valuation implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Long id;
	private Integer valuation;
	private String justification;

	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "activity_id")
	@MapsId
	private Activity activity;
	
	public Valuation() {}

	public Valuation(Long id, ValuetionEnum valuation, String justification, Activity activity) {
		super();
		this.id = id;
		this.valuation = (valuation == null) ? null : valuation.getCod();
		this.justification = justification;
		this.activity = activity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValuetionEnum getValuation() {
		return ValuetionEnum.toEnum(valuation);
	}

	public void setValuation(ValuetionEnum valuation) {
		this.valuation = valuation.getCod();
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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
		Valuation other = (Valuation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

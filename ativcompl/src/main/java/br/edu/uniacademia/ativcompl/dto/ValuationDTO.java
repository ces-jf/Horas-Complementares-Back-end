package br.edu.uniacademia.ativcompl.dto;

import br.edu.uniacademia.ativcompl.domain.Valuation;
import br.edu.uniacademia.ativcompl.domain.enums.ValuationEnum;

public class ValuationDTO {

	private Long id;
	private Integer valuation;
	private String justification;
	
	public ValuationDTO() {}
	
	public ValuationDTO(Valuation obj) {
		this.id = obj.getId();
		this.valuation = (obj.getValuation() == null) ? null : obj.getValuation().getCod();
		this.justification = obj.getJustification();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ValuationEnum getValuation() {
		return ValuationEnum.toEnum(valuation);
	}

	public void setValuation(ValuationEnum valuation) {
		this.valuation = valuation.getCod();
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
}

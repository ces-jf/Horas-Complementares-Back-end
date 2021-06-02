package br.edu.uniacademia.ativcompl.domain.enums;

public enum ValuationEnum {

	ACEITAR(1, "Aceito"),
	NEGAR_PARCIAL(2, "Negado parcialmente"),
	NEGAR_TOTAL(3, "Negado totalmente");
	
	private int cod;
	private String description;
	
	private ValuationEnum(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return description;
	}
	
	public static ValuationEnum toEnum(Integer cod) {
		
		if(cod == null) { return null;}
		
		for(ValuationEnum x : ValuationEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
}

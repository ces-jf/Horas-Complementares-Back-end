package br.edu.uniacademia.ativcompl.domain.enums;

public enum ValuetionEnum {

	ACEITAR(1, "Aceito"),
	NEGAR_PARCIAL(2, "Negado parcialmente"),
	NEGAR_TOTAL(3, "Negado totalmente");
	
	private int cod;
	private String description;
	
	private ValuetionEnum(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return description;
	}
	
	public static ValuetionEnum toEnum(Integer cod) {
		
		if(cod == null) { return null;}
		
		for(ValuetionEnum x : ValuetionEnum.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
}

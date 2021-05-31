package br.edu.uniacademia.ativcompl.domain.enums;

public enum Valuation {

	ACEITAR(1, "Coordenador"),
	NEGAR_PARCIAL(2, "Supervisor"),
	NEGAR_TOTAL(3, "Administrador");
	
	private int cod;
	private String description;
	
	private Valuation(int cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return description;
	}
	
	public static Valuation toEnum(Integer cod) {
		
		if(cod == null) { return null;}
		
		for(Valuation x : Valuation.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
		
	}
}

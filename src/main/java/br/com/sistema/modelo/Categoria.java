package br.com.sistema.modelo;

public enum Categoria {

	HATCH_COMPACTO("Hatch compacto"),
	HATCH_MEDIO("Hatch médio"),
	SEDAN_COMPACTO("Sedan compacto"),
	SEDAN_MEDIO("Sedan médio"),
	SEDAN_GRANDE("Sedan grande"),
	SUV("SUV"),
	SW_MEDIO("SW médio"),
	PICK_UPS_PEQUENAS("PICK-UPS pequena"),
	PICKUPS_GRANDE("PICK-UPS grande"),
	MINIVAN("Minivan"),
	ESPORTIVO("Esportivo"),
	UTILITARIO_COMERCIAL("Utilitário comercial");
	
	private String descricao;
	
	Categoria(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
}
	

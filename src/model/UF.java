package model;

public enum UF {
	AC("Acre"),
	AL("Alagoas"),
	AP("Amap�"),
	AM("Amazonas"),
	BA("Bahia"),
	CE("Cear�"),
	DF, ES, GO, MA, MT, MS, MG, PA, PB, PR, PE, PI, RJ, RN, RS, RO, RR, SC, SP, SE, TO;
	
	private String estado;
	
	private UF(String estado){
		this.estado = estado;
	}
	
	private UF(){	
	}
	
	public String getEstado() {
		return estado;
	}
}

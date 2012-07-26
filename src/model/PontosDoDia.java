package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PontosDoDia {
	
	private Usuario usuario;
	private Date dia;
	private List<Ponto> pontos;
	private Integer minutos;
	private String horasTrabalhadas;
	
	public PontosDoDia(){
		List<Ponto> pontosList = new ArrayList<Ponto>();
		for (int i=0; i<6; i++){
			pontosList.add(new Ponto());
		}
		this.pontos = pontosList;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Date getDia() {
		return dia;
	}
	public void setDia(Date dia) {
		this.dia = dia;
	}
	public Integer getMinutos() {
		return minutos;
	}
	public void setMinutos(Integer minutos) {
		this.minutos = minutos;
	}
	public List<Ponto> getPontos() {
		return pontos;
	}
	public void setPontos(List<Ponto> pontos) {
		this.pontos = pontos;
	}
	public String getHorasTrabalhadas() {
		return horasTrabalhadas;
	}
	public void setHorasTrabalhadas(String horasTrabalhadas) {
		this.horasTrabalhadas = horasTrabalhadas;
	}
	
}

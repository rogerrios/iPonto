package model;

import java.util.Date;
import java.util.List;

public class PontosDoDia {
	
	private Usuario usuario;
	private Date dia;
	private List<Ponto> pontos;
	private Ponto tipo0;
	private Ponto tipo1;
	private Ponto tipo2;
	private Ponto tipo3;
	private Ponto tipo4;
	private Ponto tipo5;
	private Integer minutos;
	
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
	public Ponto getTipo0() {
		return tipo0;
	}
	public void setTipo0(Ponto tipo0) {
		this.tipo0 = tipo0;
	}
	public Ponto getTipo1() {
		return tipo1;
	}
	public void setTipo1(Ponto tipo1) {
		this.tipo1 = tipo1;
	}
	public Ponto getTipo2() {
		return tipo2;
	}
	public void setTipo2(Ponto tipo2) {
		this.tipo2 = tipo2;
	}
	public Ponto getTipo3() {
		return tipo3;
	}
	public void setTipo3(Ponto tipo3) {
		this.tipo3 = tipo3;
	}
	public Ponto getTipo4() {
		return tipo4;
	}
	public void setTipo4(Ponto tipo4) {
		this.tipo4 = tipo4;
	}
	public Ponto getTipo5() {
		return tipo5;
	}
	public void setTipo5(Ponto tipo5) {
		this.tipo5 = tipo5;
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
	
}

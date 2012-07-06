package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pontos")
public class Ponto {
	@Id
	@GeneratedValue
	private Long id_ponto;
	private Date ponto;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	private String ip;	
	private Date pontoEdit;
	@ManyToOne
	@JoinColumn(name="id_usuarioEdit")
	private Usuario usuarioEdit;
	
	public Long getId_ponto() {
		return id_ponto;
	}
	public void setId_ponto(Long id_ponto) {
		this.id_ponto = id_ponto;
	}
	public Date getPonto() {
		return ponto;
	}
	public void setPonto(Date ponto) {
		this.ponto = ponto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Date getPontoEdit() {
		return pontoEdit;
	}
	public void setPontoEdit(Date pontoEdit) {
		this.pontoEdit = pontoEdit;
	}
	public Usuario getUsuarioEdit() {
		return usuarioEdit;
	}
	public void setUsuarioEdit(Usuario usuarioEdit) {
		this.usuarioEdit = usuarioEdit;
	}
	
	
}

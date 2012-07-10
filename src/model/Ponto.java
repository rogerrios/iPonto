package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="pontos")
public class Ponto {
	@Id
	@GeneratedValue
	private Long id_ponto;
	@ManyToOne
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora_ponto;
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora_salva;
	@ManyToOne
	@JoinColumn(name="id_usuario_edit")
	private Usuario usuarioEdit;
	private Integer tipo;
	private String ip;
	
	public Long getId_ponto() {
		return id_ponto;
	}
	public void setId_ponto(Long id_ponto) {
		this.id_ponto = id_ponto;
	}
	public Date getHora_ponto() {
		return hora_ponto;
	}
	public void setHora_ponto(Date ponto) {
		this.hora_ponto = ponto;
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
	public Date getHora_salva() {
		return hora_salva;
	}
	public void setHora_salva(Date pontoEdit) {
		this.hora_salva = pontoEdit;
	}
	public Usuario getUsuarioEdit() {
		return usuarioEdit;
	}
	public void setUsuarioEdit(Usuario usuarioEdit) {
		this.usuarioEdit = usuarioEdit;
	}
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	
	
}

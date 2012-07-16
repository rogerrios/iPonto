package managedBeans;

import hibernate.EditUsuarioHibernate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import model.Usuario;
import util.CriaHttpSession;

public class MinhaContaBean {
	private Usuario usuario;
	private HttpSession session;
	private String senhaAtual;
	private String novaSenha;
	
	public void alterarSenha(){
		FacesContext context = FacesContext.getCurrentInstance();
		if (senhaAtual.equals(usuario.getSenha())){
			usuario.setSenha(novaSenha);
			new EditUsuarioHibernate().updateUsuario(usuario);
			context.addMessage("msgSenhaAlterada", new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Senha alterada com sucesso"));
		} else {
			context.addMessage("msgSenhaAlterada", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro","Senha incorreta"));
		}
	}
	
	public void editUsuario(){
		new EditUsuarioHibernate().updateUsuario(usuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("msgSucesso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso",usuario.getLogin()+" alterado com sucesso"));
	}
	
	public MinhaContaBean(){
		session = new CriaHttpSession().getSession();
		usuario = (Usuario) session.getAttribute("usuario");		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getSenhaAtual() {
		return senhaAtual;
	}

	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
}

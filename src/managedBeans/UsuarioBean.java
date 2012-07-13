package managedBeans;

import hibernate.UsuarioHibernate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import model.Usuario;

public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String msgLogin;
	private HttpSession session = new CriaHttpSession().getSession();
	
	public void cadUsuario(ActionEvent event){			
		Usuario u = (Usuario) session.getAttribute("usuario");
		usuario.setCliente(u.getCliente());
		
		UsuarioHibernate uh = new UsuarioHibernate();
		if (!uh.existeLogin(usuario)){
			uh.novoUsuario(usuario);      
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("msgSucesso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", usuario.getLogin()+" cadastrado com sucesso"));
	        usuario = new Usuario();
		}
	}
	
	public void validaLogin(){
		UsuarioHibernate uh = new UsuarioHibernate();
		if (uh.existeLogin(usuario)){
			msgLogin = "* Login já existe";
		} else {
			msgLogin = null;
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMsgLogin() {
		return msgLogin;
	}

	public void setMsgLogin(String msgLogin) {
		this.msgLogin = msgLogin;
	}
}

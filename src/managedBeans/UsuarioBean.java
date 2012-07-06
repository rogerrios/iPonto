package managedBeans;

import hibernate.UsuarioHibernate;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import model.Usuario;

public class UsuarioBean {
	private Usuario usuario = new Usuario();
	private String msgLogin;
	private String msgCadastro;
	private HttpSession session = new CriaHttpSession().getSession();
	
	public void cadUsuario(ActionEvent event){			
		Usuario u = (Usuario) session.getAttribute("usuario");
		usuario.setCliente(u.getCliente());
		
		UsuarioHibernate uh = new UsuarioHibernate();
		if (!uh.existeLogin(usuario)){
			uh.novoUsuario(usuario);	        
	        msgCadastro = "Usuário "+usuario.getLogin()+" cadastrado com sucesso";
	        usuario = new Usuario();
		} else {
			msgCadastro = null;
		}
	}
	
	public void validaLogin(){
		msgCadastro = null;
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

	public String getMsgCadastro() {
		return msgCadastro;
	}

	public void setMsgCadastro(String msgCadastro) {
		this.msgCadastro = msgCadastro;
	}
}

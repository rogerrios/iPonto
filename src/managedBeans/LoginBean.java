package managedBeans;

import hibernate.LoginHibernate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpSession;

import util.CriaHttpSession;
import util.EnviaEmail;

import model.Usuario;

public class LoginBean {
	private Usuario usuario = new Usuario();
	private HttpSession session = new CriaHttpSession().getSession();
	private String email;
	
	public void esqueciSenha(ActionEvent ae) throws AddressException, MessagingException{
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario u = new LoginHibernate().getUsuarioPorEmail(email);
		
		if (u != null){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Ok", "Email enviado"));
			new EnviaEmail().recuperaSenhaEmail(u);
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Nao existe usuario para este email"));
		}
	}
	
	public String fazLogin(){	
		usuario = new LoginHibernate().fazLogin(usuario);
		
		if (usuario.getPermissao() == null){
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Usuario e/ou senha invalidos"));			
			return null;
		} else { 
			session.setAttribute("usuario", usuario);
			return "registroDePonto";
		}
	}
	
	public String logOut(){
		session.invalidate();
		return "index";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

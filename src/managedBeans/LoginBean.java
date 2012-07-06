package managedBeans;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import hibernate.LoginHibernate;
import model.Usuario;

public class LoginBean {
	private Usuario usuario = new Usuario();
	private String msg;
	private HttpSession session;
	
	public String fazLogin(){
		
		usuario = new LoginHibernate().fazLogin(usuario);
		
		if (usuario.getPermissao() == null){
			msg = "Usuário e/ou senha inválidos";
			return null;
		} else { 
			this.session.setAttribute("usuario", usuario);
			return "novoUsuario";
		}
	}
	
	public String logOut(){
		session.invalidate();
		return "index";
	}
	
	public LoginBean(){
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		this.session = request.getSession();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}

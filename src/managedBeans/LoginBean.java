package managedBeans;

import hibernate.LoginHibernate;

import javax.servlet.http.HttpSession;

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
			session = new CriaHttpSession().getSession();
			session.setAttribute("usuario", usuario);
			return "registroDePonto";
		}
	}
	
	public String logOut(){
		this.session.invalidate();
		return "index";
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

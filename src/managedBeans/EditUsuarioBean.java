package managedBeans;

import hibernate.EditUsuarioHibernate;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import util.CriaHttpSession;

import model.Usuario;

public class EditUsuarioBean {
	private Usuario editUsuario;
	private List<Usuario> colaboradoresList;
	private String filtroPermissao;
	private int id_usuario_editado;
	private String novaSenha;
	private HttpSession session = new CriaHttpSession().getSession();
	
	public void updateUsuario(){
		new EditUsuarioHibernate().updateUsuario(editUsuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("msgSucesso", new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", editUsuario.getLogin()+" alterado com sucesso"));
	}
	
	public void alterarSenha(){	
		editUsuario.setSenha(novaSenha);
		new EditUsuarioHibernate().updateUsuario(editUsuario);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("msgSenhaAlterada", new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso","Senha alterada com sucesso"));
	}
	
	public void populaColaboradores(){
		Usuario u = (Usuario) session.getAttribute("usuario");
		Usuario uSearch = new Usuario();
		uSearch.setCliente(u.getCliente());
		uSearch.setId_usuario(u.getId_usuario());
		uSearch.setPermissao(filtroPermissao);
		colaboradoresList = new EditUsuarioHibernate().buscarColaboradores(uSearch);
	}
	
	public void setaUsuarioEdicao(){
		for (Usuario u : colaboradoresList){
			if (u.getId_usuario() == id_usuario_editado){		
				editUsuario = u;
			}
		}
	}
	
	public Usuario getEditUsuario() {
		return editUsuario;
	}

	public void setEditUsuario(Usuario editUsuario) {
		this.editUsuario = editUsuario;
	}

	public List<Usuario> getColaboradoresList() {
		return colaboradoresList;
	}

	public void setColaboradoresList(List<Usuario> colaboradoresList) {
		this.colaboradoresList = colaboradoresList;
	}

	public String getFiltroPermissao() {
		return filtroPermissao;
	}

	public void setFiltroPermissao(String filtroPermissao) {
		this.filtroPermissao = filtroPermissao;
	}

	public int getId_usuario_editado() {
		return id_usuario_editado;
	}

	public void setId_usuario_editado(int id_usuario_editado) {
		this.id_usuario_editado = id_usuario_editado;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}
	
}

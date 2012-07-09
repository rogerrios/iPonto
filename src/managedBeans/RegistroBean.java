package managedBeans;

import hibernate.RegistraPontoHibernate;

import java.util.Date;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Ponto;
import model.Usuario;

public class RegistroBean {
	private HttpSession session = new CriaHttpSession().getSession();
	
	public String registrarPonto() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String ip = request.getRemoteAddr();
				
		Usuario u = (Usuario) session.getAttribute("usuario");
		RegistraPontoHibernate rph = new RegistraPontoHibernate();
		Integer tipo = rph.tipoDoProxregistro(new Date(), u);
		
		Ponto ponto = new Ponto();
		ponto.setIp(ip);
		ponto.setPonto(new Date());
		ponto.setPontoEdit(new Date());
		ponto.setUsuario(u);
		ponto.setUsuarioEdit(u);
		ponto.setTipo(tipo);
		
		rph.registraPonto(ponto);
		
		return null;
	}
}

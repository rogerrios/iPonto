package managedBeans;

import hibernate.RegistraPontoHibernate;
import hibernate.RelatoriosHibernate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Ponto;
import model.PontosDoDia;
import model.Usuario;

public class RegistroBean {
	
	private HttpSession session = new CriaHttpSession().getSession();
	//private List<Ponto> pontosDoMes;
	private List<PontosDoDia> pontosPorDia;
	
	public void populaPontosDoMes(){
		Usuario u = (Usuario) session.getAttribute("usuario");
		List<Ponto> pontosDoMes = new RelatoriosHibernate().getPontosDoMes(new Date(), u);
		
		pontosPorDia.clear();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		}
		
	
	
	public void registrarPonto(ActionEvent ae){
		FacesContext context = FacesContext.getCurrentInstance();  
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String ip = request.getRemoteAddr();
				
		Usuario u = (Usuario) session.getAttribute("usuario");
		RegistraPontoHibernate rph = new RegistraPontoHibernate();
		Integer tipo = null;
		Date dt = new Date();
		
		try {
			tipo = rph.tipoDoProxregistro(dt, u);
		} catch (Exception e) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Limite de registros diarios excedido"));
		}
		
		if (tipo != null){
			
			Ponto ponto = new Ponto();
			ponto.setIp(ip);
			ponto.setHora_ponto(dt);
			ponto.setHora_salva(dt);
			ponto.setUsuario(u);
			ponto.setUsuarioEdit(u);
			ponto.setTipo(tipo);
		
			rph.registraPonto(ponto);
			
			SimpleDateFormat df = new SimpleDateFormat("HH:mm");
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Ponto registrado as "+df.format(dt)));
		}
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}

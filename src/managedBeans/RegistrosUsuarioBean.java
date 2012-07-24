package managedBeans;

import hibernate.EditUsuarioHibernate;
import hibernate.RegistraPontoHibernate;
import hibernate.RegistrosUsuarioHibernate;
import hibernate.RelatoriosHibernate;

import java.text.ParseException;
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
import util.CriaHttpSession;
import util.MinutosEmHoras;

public class RegistrosUsuarioBean {
	
	private HttpSession session;
	private List<Usuario> colaboradoresList;
	private Usuario usuarioEditado;
	private List<String> anos;
	private Integer ano;
	private String mes;
	private List<PontosDoDia> pontosDoMes;
	private List<PontosDoDia> pontosDoMesCopy;
	private String horasTrabalhadasMes;
	private Integer diasTrabalhadosMes;
	private Ponto pontoEditado;
	private static FacesMessage MSG_PONTO_INVALIDO;
	private static FacesMessage MSG_PONTO_EDITADO;
	
	public void deletePonto(ActionEvent ae){
		new RegistrosUsuarioHibernate().deletarPonto(pontoEditado);
		pontosDoMesValue();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro deletado",  "Deletado");	
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	public void salvarPonto(ActionEvent ae) throws ParseException{
		pontosDoMesCopy = new RelatoriosHibernate().getPontosDoMes(mesAno(), usuarioEditado);
		
		SimpleDateFormat dfHora = new SimpleDateFormat("HH:mm");
		SimpleDateFormat dfDia = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat dfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest)context.getExternalContext().getRequest();
		String ip = request.getRemoteAddr();
		
		Usuario u = (Usuario) session.getAttribute("usuario");
		
		boolean stop = false;		
		for (int i=0; i < pontosDoMes.size(); i++){
			if (stop){
				break;
			}
			
			List<Ponto> pList = pontosDoMes.get(i).getPontos();
			List<Ponto> pListCopy = pontosDoMesCopy.get(i).getPontos();
			
			String strDia = dfDia.format(pontosDoMes.get(i).getDia());
	
			for (int j=0; j < pList.size(); j++){
				Date dtNova = pList.get(j).getHora_ponto();
				
				Ponto p = pListCopy.get(j);
				p.setHora_salva(new Date());
				p.setIp(ip);
				p.setUsuarioEdit(u);
				
				Date dtAntiga = p.getHora_ponto();
				
				if (dtNova==null && dtAntiga != null){				
					context.addMessage(null, MSG_PONTO_INVALIDO);
					pontosDoMesValue();
					stop = true;
					break;
				}
		
				//Se as duas datas forem diferentes de null, o ponto está sendo editado
				else if (dtNova!=null && dtAntiga!=null){
					String strNova = dfHora.format(dtNova);
					String strAntiga = dfHora.format(dtAntiga);
					
					if (!strNova.equals(strAntiga)){
						Date novoPonto = dfDiaHora.parse(strDia+" "+strNova);
						Date pontoAnterior = new Date();
						Date pontoPosterior = new Date();

						if (j == 0){
							pontoAnterior.setTime(novoPonto.getTime());
							pontoPosterior = pListCopy.get(j+1).getHora_ponto();
						} else if (j == pListCopy.size()-1){
							pontoPosterior.setTime(novoPonto.getTime());
							pontoAnterior = pListCopy.get(j-1).getHora_ponto();
						} else {
							pontoAnterior = pListCopy.get(j-1).getHora_ponto();
							pontoPosterior = pListCopy.get(j+1).getHora_ponto();
						}
					
						if (pontoPosterior==null && novoPonto.compareTo(pontoAnterior)>=0 || pontoPosterior!=null && novoPonto.compareTo(pontoPosterior)<=0 && novoPonto.compareTo(pontoAnterior)>=0){
							p.setHora_ponto(novoPonto);						
							new RegistraPontoHibernate().updatePonto(p);
							context.addMessage(null, MSG_PONTO_EDITADO);
						} else {
							context.addMessage(null, MSG_PONTO_INVALIDO);
						}
						
						pontosDoMesValue();
						stop = true;
						break;
					}
				}
				
				//se dtNova nao for null e dtAntiga for null, um novo ponto será registrado
				else if (dtNova!=null && dtAntiga==null){
					String strNova = dfHora.format(dtNova);
					Date novoPonto = dfDiaHora.parse(strDia+" "+strNova);
					Date pontoAnterior = pListCopy.get(j-1).getHora_ponto();
					
					if (novoPonto.compareTo(pontoAnterior)>=0){
						p.setHora_ponto(novoPonto);				
						p.setUsuario(usuarioEditado);
						
						Integer tipo = null;
						RegistraPontoHibernate rph = new RegistraPontoHibernate();
						try {
							tipo = rph.tipoDoProxregistro(novoPonto, usuarioEditado);
						} catch (Exception e) {
							context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Limite de registros do dia excedido", ""));
						}
						
						if (tipo != null){
							rph.registraPonto(p);
							context.addMessage(null, MSG_PONTO_EDITADO);
						}
					} else {
						context.addMessage(null, MSG_PONTO_INVALIDO);
					}
					
					
								
					pontosDoMesValue();
					stop = true;
					break;
				}
			}
		}
	}
	
	public void pontosDoMesValue(){
		pontosDoMes = new RelatoriosHibernate().getPontosDoMes(mesAno(), usuarioEditado);
		
		int minutosTrabalhados = 0;
		for (PontosDoDia p : pontosDoMes){
			minutosTrabalhados += p.getMinutos();
		}

		diasTrabalhadosMes = pontosDoMes.size();
		horasTrabalhadasMes = new MinutosEmHoras().minutosEmHoras(minutosTrabalhados);
		usuarioEditado = pontosDoMes.get(0).getUsuario();
	}
	
	public void getAnosValue(){
		anos = new RelatoriosHibernate().getAnos(usuarioEditado);
	}

	public List<Usuario> getColaboradoresList() {
		Usuario u = (Usuario) session.getAttribute("usuario");
		Usuario uSearch = new Usuario();
		uSearch.setCliente(u.getCliente());
		colaboradoresList = new EditUsuarioHibernate().buscarColaboradores(uSearch);
		return colaboradoresList;
	}
	
	public Date mesAno(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date dt = null;
		try {
			dt = df.parse(ano+mes);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	public RegistrosUsuarioBean(){
		session = new CriaHttpSession().getSession();
		horasTrabalhadasMes = "00:00";
		diasTrabalhadosMes = 0;
		usuarioEditado = new Usuario();
		pontoEditado = new Ponto();
		MSG_PONTO_INVALIDO = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Ponto invalido", "");
		MSG_PONTO_EDITADO = new FacesMessage(FacesMessage.SEVERITY_INFO,"Ponto editado", "");
	}

	public void setColaboradoresList(List<Usuario> colaboradoresList) {
		this.colaboradoresList = colaboradoresList;
	}

	public List<String> getAnos() {
		return anos;
	}

	public void setAnos(List<String> anos) {
		this.anos = anos;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public List<PontosDoDia> getPontosDoMes() {
		return pontosDoMes;
	}

	public void setPontosDoMes(List<PontosDoDia> pontosDoMes) {
		this.pontosDoMes = pontosDoMes;
	}

	public String getHorasTrabalhadasMes() {
		return horasTrabalhadasMes;
	}

	public void setHorasTrabalhadasMes(String horasTrabalhadasMes) {
		this.horasTrabalhadasMes = horasTrabalhadasMes;
	}

	public Integer getDiasTrabalhadosMes() {
		return diasTrabalhadosMes;
	}

	public void setDiasTrabalhadosMes(Integer diasTrabalhadosMes) {
		this.diasTrabalhadosMes = diasTrabalhadosMes;
	}

	public Usuario getUsuarioEditado() {
		return usuarioEditado;
	}

	public void setUsuarioEditado(Usuario usuarioEditado) {
		this.usuarioEditado = usuarioEditado;
	}

	public Ponto getPontoEditado() {
		return pontoEditado;
	}

	public void setPontoEditado(Ponto pontoEditado) {
		this.pontoEditado = pontoEditado;
	}
}

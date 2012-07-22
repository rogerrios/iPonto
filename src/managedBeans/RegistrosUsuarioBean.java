package managedBeans;

import hibernate.EditUsuarioHibernate;
import hibernate.RelatoriosHibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import model.Ponto;
import model.PontosDoDia;
import model.Usuario;
import util.CriaHttpSession;
import util.MinutosEmHoras;

public class RegistrosUsuarioBean {
	
	private HttpSession session;
	private List<Usuario> colaboradoresList;
	private int id_usuario_editado;
	private Usuario usuarioEditado;
	private List<String> anos;
	private Integer ano;
	private String mes;
	private List<PontosDoDia> pontosDoMes;
	private List<PontosDoDia> pontosDoMesCopy;
	private String horasTrabalhadasMes;
	private Integer diasTrabalhadosMes;
	
	public void handleSave(){
		System.out.println("saved");
	}
	
	public void salvarPontos(){
		pontosDoMesCopy = new RelatoriosHibernate().getPontosDoMes(mesAno(), usuarioEditado);
		
		for (int i=0; i < pontosDoMes.size(); i++){
			List<Ponto> pList = pontosDoMes.get(i).getPontos();
			List<Ponto> pListCopy = pontosDoMesCopy.get(i).getPontos();
			
			for (int j=0; j < pList.size(); j++){
				Date dtNova = pList.get(j).getHora_ponto();
				Date dtAntiga = pListCopy.get(j).getHora_ponto();
				
				if (dtNova!=null && dtAntiga!=null && !dtNova.equals(dtAntiga)){
					
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
		Date dt=null;
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
	}

	public void setColaboradoresList(List<Usuario> colaboradoresList) {
		this.colaboradoresList = colaboradoresList;
	}

	public int getId_usuario_editado() {
		return id_usuario_editado;
	}

	public void setId_usuario_editado(int id_usuario_editado) {
		this.id_usuario_editado = id_usuario_editado;
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
}

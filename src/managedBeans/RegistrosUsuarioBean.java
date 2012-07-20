package managedBeans;

import hibernate.EditUsuarioHibernate;
import hibernate.RelatoriosHibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

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
	private String horasTrabalhadasMes;
	private Integer diasTrabalhadosMes;
	
	public void pontosDoMesValue() throws ParseException{
		Usuario u = new Usuario();
		u.setId_usuario(id_usuario_editado);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date dt = df.parse(ano+mes);

		pontosDoMes = new RelatoriosHibernate().getPontosDoMes(dt, u);
		
		List<PontosDoDia> pontosDoMesCopy = pontosDoMes.subList(0, pontosDoMes.size());

		int minutosTrabalhados = 0;
		for (PontosDoDia p : pontosDoMes){
			minutosTrabalhados += p.getMinutos();
		}

		diasTrabalhadosMes = pontosDoMes.size();
		horasTrabalhadasMes = new MinutosEmHoras().minutosEmHoras(minutosTrabalhados);
		usuarioEditado = pontosDoMes.get(0).getUsuario();
	}
	
	public void getAnosValue(){
		Usuario u = new Usuario();
		u.setId_usuario(id_usuario_editado);
		anos = new RelatoriosHibernate().getAnos(u);
	}

	public List<Usuario> getColaboradoresList() {
		Usuario u = (Usuario) session.getAttribute("usuario");
		Usuario uSearch = new Usuario();
		uSearch.setCliente(u.getCliente());
		colaboradoresList = new EditUsuarioHibernate().buscarColaboradores(uSearch);
		return colaboradoresList;
	}
	
	public RegistrosUsuarioBean(){
		session = new CriaHttpSession().getSession();
		horasTrabalhadasMes = "00:00";
		diasTrabalhadosMes = 0;
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

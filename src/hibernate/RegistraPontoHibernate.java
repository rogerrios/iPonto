package hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Usuario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RegistraPontoHibernate {
	
	private SessionFactory factory;
	
	public RegistraPontoHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public String tipoDoUltimoRegistro(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Session session = factory.openSession();
		Query query = session.createQuery("select max(tipo) from ponto where login = :paramLogin and ponto = :paramData");
		query.setParameter("paramData", df.format(dt));
		
		String tipo = (String)query.uniqueResult();
		
		if (tipo == null){
			tipo = "INI_MANHA";
		}
		
		session.close();
		return tipo;
	}
	public int tipoDoProxregistro() throws Exception{
		int tipo = tipoDoUltimoRegistro();
		if (tipo == 5){
			throw new Exception("Limite de registros diários excedido.");
		} else {
			tipo++;
		}
		return tipo;
	}
}

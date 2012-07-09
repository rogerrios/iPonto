package hibernate;

import java.text.SimpleDateFormat;
import java.util.Date;

import model.Ponto;
import model.Usuario;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RegistraPontoHibernate {
	
	private SessionFactory factory;
	
	public RegistraPontoHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public void registraPonto(Ponto p){
		Session session = factory.openSession();					
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();		
	}
	
	public Integer tipoDoUltimoRegistro(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Session session = factory.openSession();
		Query query = session.createQuery("select max(tipo) from ponto where id_usuario = :paramID and registroponto = :paramData");
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramData", df.format(dt));
		
		Integer tipo = (Integer)query.uniqueResult();
		
		if (tipo == null){
			tipo = 0;
		}
		
		session.close();
		return tipo;
	}
	
	public Integer tipoDoProxregistro(Date dt, Usuario u) throws Exception{
		Integer tipo = tipoDoUltimoRegistro(dt, u);
		
		if (tipo == 5){
			throw new Exception("Limite de registros diários excedido.");
		} else {
			tipo++;
		}		
		return tipo;
	}
}

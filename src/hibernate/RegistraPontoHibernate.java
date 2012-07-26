package hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Ponto;
import model.Usuario;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RegistraPontoHibernate {
	
	private SessionFactory factory;
	
	public RegistraPontoHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public void registraPonto(Ponto p){
		
		//Zerando os segundos do ponto
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String data = df.format(p.getHora_ponto());		
		try {
			p.setHora_ponto(df.parse(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Session session = factory.openSession();					
		session.beginTransaction();
		session.save(p);
		session.getTransaction().commit();
		session.close();		
	}
	
	public void updatePonto(Ponto p){
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(p);
		session.getTransaction().commit();
		session.close();
	}
	
	public Integer tipoDoUltimoRegistro(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("dd");
		Session session = factory.openSession();
		SQLQuery query = session.createSQLQuery("select max(tipo) from pontos where id_usuario = :paramID and day(hora_ponto) = :paramDate");
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramDate", df.format(dt));
		
		Integer tipo = (Integer)query.uniqueResult();
			
		session.close();
		return tipo;
	}
	
	public Integer tipoDoProxregistro(Date dt, Usuario u) throws Exception{
		Integer tipo = tipoDoUltimoRegistro(dt, u);
		
		if (tipo == null){
			tipo = 0;
		} else if (tipo == 5){
			throw new Exception("Limite de registros diários excedido.");
		} else {
			tipo++;
		}		
		return tipo;
	}
}

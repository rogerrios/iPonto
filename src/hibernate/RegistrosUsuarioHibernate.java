package hibernate;

import model.Ponto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RegistrosUsuarioHibernate {
	private SessionFactory factory = new CriaSessionFactory().getFactory();
	
	public void deletarPonto(Ponto p){
		Session session = factory.openSession();
		session.beginTransaction();
		session.delete(p);
		session.getTransaction().commit();
		session.close();
	}
}

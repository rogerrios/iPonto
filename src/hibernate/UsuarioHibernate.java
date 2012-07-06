package hibernate;

import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class UsuarioHibernate {
	
	private SessionFactory factory;
	
	public UsuarioHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public void novoUsuario(Usuario u){				
		Session session = factory.openSession();					
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		session.close();
	}
	
	public boolean existeLogin(Usuario u){
		Session session = factory.openSession();		
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", u.getLogin()));
		
		if (criteria.list().isEmpty()){
			session.close();
			return false;
		} else {
			session.close();
			return true;
		}
	}
}

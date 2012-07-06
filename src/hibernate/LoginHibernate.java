package hibernate;

import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

public class LoginHibernate {
	
	private SessionFactory factory;
	
	public LoginHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public Usuario fazLogin(Usuario u){
		Session session = factory.openSession();		
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("login", u.getLogin()));
		criteria.add(Restrictions.eq("senha", u.getSenha()));
		
		Usuario u2 = (Usuario) criteria.uniqueResult();
		
		session.close();
		
		if (u2 == null){
			return u;
		} else {
			return u2;
		}
	}
}

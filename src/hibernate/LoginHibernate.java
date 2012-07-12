package hibernate;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import util.EnviaEmail;

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
			u2 = new Usuario();
		}
		return u2;
	}
	
	public boolean recuperaSenha(String email) throws AddressException, MessagingException{
		Session session = factory.openSession();		
		
		Criteria criteria = session.createCriteria(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		
		Usuario u = (Usuario) criteria.uniqueResult();
		
		session.close();
		
		if (u == null){
			return false;
		} else {
			new EnviaEmail().recuperaSenhaEmail(u);
			return true;
		}
	}
}

package hibernate;

import model.Cliente;
import model.Usuario;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CriaSessionFactory {
	private static SessionFactory factory;

	public SessionFactory getFactory() {
		if (factory == null || factory.isClosed()){
		Configuration cfg = new Configuration().addAnnotatedClass(Cliente.class);
		cfg.addAnnotatedClass(Usuario.class);
		factory = cfg.buildSessionFactory();
	}
		return factory;
	}
}

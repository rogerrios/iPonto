package hibernate;

import model.Cliente;
import model.Ponto;
import model.Usuario;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CriaSessionFactory {
	private static SessionFactory factory;

	public SessionFactory getFactory() {
		if (factory == null || factory.isClosed()){
		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(Cliente.class);
		cfg.addAnnotatedClass(Usuario.class);
		cfg.addAnnotatedClass(Ponto.class);
		
		factory = cfg.buildSessionFactory();
	}
		return factory;
	}
}

package tests;

import model.Cliente;
import model.Usuario;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class test1 {

	public static void main(String[] args) {
		
		Configuration cfg = new Configuration().addAnnotatedClass(Cliente.class);
		cfg.addAnnotatedClass(Usuario.class);
		SessionFactory factory = cfg.buildSessionFactory();
		
		Session session = factory.openSession();
		
		session.beginTransaction();
		
		Cliente cliente = new Cliente();
		cliente.setId_clientes(1);
		
		Usuario u = new Usuario();
		u.setCliente(cliente);
		u.setEmail("admin@email.com");
		u.setLogin("admin");
		u.setNome("Admin de Teste");
		u.setPermissao("MASTER");
		u.setSenha("admin");
		u.setStatus("ATIVADO");
		
		session.save(u);
		session.getTransaction().commit();
		session.close();
		factory.close();
	}

}

package tests;

import hibernate.CriaSessionFactory;
import hibernate.RelatoriosHibernate;

import java.util.Date;
import java.util.List;

import model.Cliente;
import model.PontosDoDia;
import model.Usuario;

import org.hibernate.Session;

public class test1 {

	public static void main(String[] args) throws Exception {
		
		Session session = new CriaSessionFactory().getFactory().openSession();
		
		session.beginTransaction();
		
		Cliente cliente = new Cliente();
		cliente.setId_clientes(1);
		
		Usuario u = new Usuario();
		u.setCliente(cliente);
		u.setId_usuario(1);
		u.setEmail("admin@email.com");
		u.setLogin("admin");
		u.setNome("Admin de Teste");
		u.setPermissao("MASTER");
		u.setSenha("admin");
		u.setStatus("ATIVADO");
		
		List<PontosDoDia> mes = new RelatoriosHibernate().getPontosDoMes(new Date(), u);
		System.out.println(mes);
	}

}

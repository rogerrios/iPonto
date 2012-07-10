package tests;

import hibernate.CriaSessionFactory;
import hibernate.RegistraPontoHibernate;

import java.util.Date;

import model.Cliente;
import model.Ponto;
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
		
		RegistraPontoHibernate rph = new RegistraPontoHibernate();
		//Integer tipo = rph.tipoDoProxregistro(new Date(), u);
		
		Ponto ponto = new Ponto();
		ponto.setIp("ip");
		ponto.setHora_ponto(new Date());
		ponto.setHora_salva(new Date());
		ponto.setUsuario(u);
		ponto.setUsuarioEdit(u);
		ponto.setTipo(0);
		
		rph.registraPonto(ponto);
	}

}

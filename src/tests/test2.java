package tests;

import hibernate.LoginHibernate;
import model.Usuario;

public class test2 {
	public static void main(String[] args) {
		Usuario u = new Usuario();
		u.setLogin("admin");
		u.setSenha("admin");
		
		u = new LoginHibernate().fazLogin(u);
		
		if (u.getPermissao() == null){
			System.out.println("Senha errada.");
		} else {
			System.out.println(u.getCliente().getNome_cliente());
			System.out.println("Senha correta.");
		}
	}
	
	
}

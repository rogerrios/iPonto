package hibernate;

import java.util.List;

import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

public class EditUsuarioHibernate {
	private SessionFactory factory;
	
	public EditUsuarioHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public List<Usuario> buscarColaboradores(Usuario u){
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(Usuario.class);
		
		if (u.getPermissao() != null){
			criteria.add(Restrictions.eq("permissao", u.getPermissao()));
		}
		
		criteria.add(Restrictions.eq("cliente", u.getCliente()));
		criteria.addOrder(Order.asc("nome"));
		
		@SuppressWarnings("unchecked")
		List<Usuario> lista = criteria.list();
		session.close();
		return lista;
	}
	
	public void updateUsuario(Usuario u){
		Session session = factory.openSession();
		session.beginTransaction();
		session.update(u);
		session.getTransaction().commit();
		session.close();
	}
}

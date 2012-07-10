package hibernate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Ponto;
import model.PontosDoDia;
import model.Usuario;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class RelatoriosHibernate {
	
	private SessionFactory factory;
	
	public RelatoriosHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	/*public List<Ponto> getPontosDoMes(Date mes, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Session session = factory.openSession();		
		
		SQLQuery query = session.createSQLQuery("select * from pontos where id_usuario = :paramID and month(hora_ponto) = :paramMes order by hora_ponto, tipo");
		query.addEntity(Ponto.class);
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramMes", df.format(mes));
		
		@SuppressWarnings("unchecked")
		List<Ponto> pontosList = query.list();
		
		session.close();
		
		return pontosList;
		}*/
	
	public List<Date> getDiasDoMes(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Session session = factory.openSession();		
		
		SQLQuery query = session.createSQLQuery("select distinct date(hora_ponto) from pontos where id_usuario = :paramID and EXTRACT(YEAR_MONTH from hora_ponto) = :paramMes order by hora_ponto,tipo");
		query.addEntity(Ponto.class);
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramMes", df.format(dt));

		@SuppressWarnings("unchecked")
		List<Date> dias = query.list();
		session.close();
		
		return dias;
	}
	
	public List<Ponto> getPontosDoDia(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Session session = factory.openSession();		
		
		SQLQuery query = session.createSQLQuery("select * from pontos where id_usuario = :paramID and date(hora_ponto) = :paramDia order by hora_ponto, tipo");
		query.addEntity(Ponto.class);
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramDia", df.format(dt));
		
		@SuppressWarnings("unchecked")
		List<Ponto> pontosList = query.list();
		
		session.close();
		
		return pontosList;
	}
	
	public List<PontosDoDia> getPontosDoMes(Date dt, Usuario u){
		List<Date> diasList = getDiasDoMes(dt, u);
		List<PontosDoDia> pontosDoMesList = new ArrayList<PontosDoDia>();
		
		Session session = factory.openSession();
		
		int minutos = 0;
		for (Date d : diasList){
			PontosDoDia pdd = new PontosDoDia();
			pdd.setDia(d);
			
			List<Ponto> pontos = getPontosDoDia(dt, u);
			pdd.setPontos(pontos);
			
			
			if ()
			
		}
		
		session.close();
		return pontosDoMesList;
	}
	
	/*public List<PontosDoDia> getPontosPorDia(Date mes, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("MM");
		List<Ponto> pontosMes = getPontosDoMes(mes, u);
		List<PontosDoDia> pontosDoDiaList = new ArrayList<PontosDoDia>();
		
		Session session = factory.openSession();				
		SQLQuery query = session.createSQLQuery("select distinct EXTRACT(YEAR_MONTH from hora_ponto) from pontos where id_usuario = :paramID and EXTRACT(YEAR_MONTH from hora_ponto) = :paramMes order by hora_ponto,tipo");
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramMes", df.format(mes));
		List<Date> diasList = query.list();
		session.close();
		
		for (Date dia : diasList){
			PontosDoDia pdd = new PontosDoDia();
			pdd.setDia(dia);
			pontosDoDiaList.add(pdd);
		}
		
		for (PontosDoDia pdd : pontosDoDiaList){
			if (pontosMes.g)
		}

		return pontosDoDiaList;
	}*/
	
}

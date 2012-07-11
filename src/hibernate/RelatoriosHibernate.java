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

import util.MinutosEmHoras;

public class RelatoriosHibernate {
	
	private SessionFactory factory;
	
	public RelatoriosHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public List<Date> getDiasDoMes(Date dt, Usuario u){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Session session = factory.openSession();		
		
		SQLQuery query = session.createSQLQuery("select distinct date(hora_ponto) from pontos where id_usuario = :paramID and EXTRACT(YEAR_MONTH from hora_ponto) = :paramMes order by hora_ponto,tipo");
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
		MinutosEmHoras meh = new MinutosEmHoras();
		
		Session session = factory.openSession();
		
		int minutos = 0;
		
		for (Date d : diasList){
			PontosDoDia pdd = new PontosDoDia();
			pdd.setDia(d);
			
			List<Ponto> pontos = getPontosDoDia(d, u);
			pdd.setPontos(pontos);
			
			//Calcula minutos trabalhados no dia
			for (int i = 0; i < pontos.size(); i++){
				if (i % 2 != 0){
					long horaMenor = pontos.get(i-1).getHora_ponto().getTime();
					long horaMaior = pontos.get(i).getHora_ponto().getTime();
					minutos += ((horaMaior-horaMenor) / 1000 / 60);
				}
			}
			pdd.setMinutos(minutos);			
			pdd.setHorasTrabalhadas(meh.minutosEmHoras(minutos));
			
			pontosDoMesList.add(pdd);
		}
		session.close();
		return pontosDoMesList;
	}
	
}

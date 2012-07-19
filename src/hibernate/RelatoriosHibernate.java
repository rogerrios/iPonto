package hibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.Ponto;
import model.PontosDoDia;
import model.Usuario;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import util.MinutosEmHoras;

public class RelatoriosHibernate {
	
	private SessionFactory factory;
	
	public RelatoriosHibernate(){
		this.factory = new CriaSessionFactory().getFactory();
	}
	
	public List<String> getAnos(Usuario u){
		Session session = factory.openSession();		
		
		SQLQuery query = session.createSQLQuery("select distinct year(hora_ponto) from pontos where id_usuario = :paramID order by year(hora_ponto) desc");
		query.setParameter("paramID", u.getId_usuario());
		
		@SuppressWarnings("unchecked")
		List<String> anos = query.list();
		session.close();
		
		return anos;
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
	
	public List<Ponto> getPontosDoDia(Date dt, Usuario u) throws ParseException{
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DAY_OF_YEAR, 1);
		
		Date dt2 = df.parse(df.format(cal.getTime()));
		
		Session session = factory.openSession();
		
		/*SQLQuery query = session.createSQLQuery("select * from pontos where id_usuario = :paramID and date(hora_ponto) = :paramDia order by hora_ponto, tipo");
		query.addEntity(Ponto.class);
		query.setParameter("paramID", u.getId_usuario());
		query.setParameter("paramDia", df.format(dt));
		
		@SuppressWarnings("unchecked")
		List<Ponto> pontosList = query.list();*/
		
		Criteria criteria = session.createCriteria(Ponto.class);
		criteria.add(Restrictions.eq("usuario",u));
		criteria.add(Restrictions.ge("hora_ponto",dt));
		criteria.add(Restrictions.lt("hora_ponto",dt2));

		@SuppressWarnings("unchecked")
		List<Ponto> pontosList = criteria.list();
		session.close();
		return pontosList;
	}
	
	public List<PontosDoDia> getPontosDoMes(Date dt, Usuario u) throws ParseException{
		List<Date> diasList = getDiasDoMes(dt, u);
		List<PontosDoDia> pontosDoMesList = new ArrayList<PontosDoDia>();
		
		MinutosEmHoras meh = new MinutosEmHoras();
		
		for (Date d : diasList){					
			PontosDoDia pdd = new PontosDoDia();
			pdd.setDia(d);
			
			List<Ponto> pontos = getPontosDoDia(d, u);
			pdd.setPontos(pontos);
			
			//Calcula minutos trabalhados no dia
			int minutos = 0;
			for (int i = 0; i < pontos.size(); i++){
				if (i % 2 != 0){
					long horaMenor = pontos.get(i-1).getHora_ponto().getTime();
					long horaMaior = pontos.get(i).getHora_ponto().getTime();
					minutos += ((horaMaior-horaMenor) / 1000 / 60);
				}
			}
			pdd.setMinutos(minutos);			
			pdd.setHorasTrabalhadas(meh.minutosEmHoras(minutos));
			pdd.setUsuario(pontos.get(0).getUsuario());
			
			pontosDoMesList.add(pdd);
		}
		
		for (PontosDoDia pdd : pontosDoMesList){
			int p = pdd.getPontos().size();
			if (p < 6){
				for (int i=0; i < -p+6; i++){
					Ponto pto = new Ponto();
					pdd.getPontos().add(pto);
				}
			}
		}
		return pontosDoMesList;
	}
	
}

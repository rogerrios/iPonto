package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MesclaDataHora {
	
	private SimpleDateFormat dfHora = new SimpleDateFormat("HH:mm");
	private SimpleDateFormat dfDia = new SimpleDateFormat("dd/MM/yyyy");
	private SimpleDateFormat dfDiaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public Date Mesclar(Date data, Date hora){
		String strDia = dfDia.format(data);
		String strHora = dfHora.format(hora);
		
		Date dt = null;
		try {
			dt = dfDiaHora.parse(strDia+" "+strHora);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dt;
	}
}

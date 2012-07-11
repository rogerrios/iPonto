package util;

public class MinutosEmHoras {
	
	public String minutosEmHoras(Integer x){
		
		String hora = "";
		String min = "";
		
		if (x/60 < 10){
			hora += "0"+x/60;
		} else {
			hora += x/60;
		}
		
		if (x%60 < 10){
			min += "0" + x % 60;
		} else {
			min += x % 60;
		}

		return hora + ":" + min;
	}
}

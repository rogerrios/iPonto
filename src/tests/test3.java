package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test3 {


	public static void main(String[] args) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyy HH:mm");
		Date dt = new Date();
		long l = dt.getTime();
		
		Date dt2 = df.parse("11/07/2012 13:00");
		long l2 = dt2.getTime();
		
		dt.setTime(l2-l);
		
		df.applyPattern("HH:mm");
		
		System.out.println(df.format(dt));
	}

}

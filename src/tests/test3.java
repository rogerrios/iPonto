package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class test3 {


	public static void main(String[] args) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		Date dt = null;
		try {
			dt = df.parse("201202");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(dt);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int d = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, d);
		
		System.out.println(cal.getTime());
	}

}

package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class test3 {


	public static void main(String[] args) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
		//System.out.println(df.getTimeZone());
		//df.setTimeZone(TimeZone.getTimeZone("Etc/GMT-3"));
		Date dt = df.parse("201201");
		System.out.println(dt);
		System.out.println(TimeZone.getDefault());
		
	}

}

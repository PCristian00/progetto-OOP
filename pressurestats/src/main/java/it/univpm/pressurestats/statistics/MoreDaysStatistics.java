package it.univpm.pressurestats.statistics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONObject;

public class MoreDaysStatistics extends Statistics{
	
	@SuppressWarnings("unchecked")
	public JSONObject MoreDays(String city, int numDays)
	{
		JSONObject statsMoreDays = new JSONObject();
		
		for(int i = 0; i<numDays; i++)
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String day = df.format(cal.getTime());
			
			statsMoreDays.put(day, OneDay(city, day));
		}
		
		
		
		return statsMoreDays;
	}
}

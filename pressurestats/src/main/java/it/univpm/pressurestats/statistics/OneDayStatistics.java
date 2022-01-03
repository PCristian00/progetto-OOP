package it.univpm.pressurestats.statistics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONObject;


public class OneDayStatistics {
	
	@SuppressWarnings("unchecked")
	public JSONObject OneDay(String city, int numDays)
	{
		JSONObject object;
		JSONObject statistics = new JSONObject();
		
		statistics.put("city", city);
		
		for(int i = 0; i<numDays; i++)
		{
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String day = df.format(cal.getTime());
			
			Statistics stats = new Statistics(city, day);
			object = new JSONObject();
			
			object.put("average", stats.avg());
			object.put("variance", stats.variance());
			object.put("maxMin", stats.MaxMin());
			
			statistics.put(day, object);
		}
		
		return statistics;
	}
}

package it.univpm.pressurestats.statistics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Statistics {
	
	@SuppressWarnings("unchecked")
	public JSONObject OneDay(String city, String day)
	{
		OneDayStatistics od = new OneDayStatistics();
		JSONArray stats = od.oneDayWeather(city, day);
		JSONArray vispre = new JSONArray();
		JSONObject weather = new JSONObject();
		JSONObject pressureobj = new JSONObject();
		JSONObject average = new JSONObject();
		JSONObject variance = new JSONObject();
		JSONObject maxmin = new JSONObject();
		JSONObject object = new JSONObject();
		
		long visibility = 0;
		long pressure = 0;
		long pressureavg = 0;
		long visibilityavg = 0;
		long visibilitymin = 0;
		long visibilitymax = 0;
		long pressuremax = 0;
		long pressuremin = 0;
		long pressurevar = 0;
		long visibilityvar = 0;
		
		int j = 0;
		
		for(int i = 0; i<stats.size(); i++)
		{
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			if(pressureobj.get("date").toString().substring(0, 10).equals(day))
			{
				if(j == 0) {
					pressuremax = (long) pressureobj.get("pressure");
					pressuremin = (long) pressureobj.get("pressure");
					visibilitymax = (long) pressureobj.get("visibility");
					visibilitymin = (long) pressureobj.get("visibility");
				}
				
				
				visibility += (long) pressureobj.get("visibility");
				if(visibilitymax < (long) pressureobj.get("visibility"))
					visibilitymax = (long) pressureobj.get("visibility");
				else if (visibilitymin > (long) pressureobj.get("visibility"))
					visibilitymin = (long) pressureobj.get("visibility");
				
				
				pressure += (long) pressureobj.get("pressure");
				if(pressuremax < (long) pressureobj.get("pressure"))
					pressuremax = (long) pressureobj.get("pressure");
				else if (pressuremin > (long) pressureobj.get("pressure"))
					pressuremin = (long) pressureobj.get("pressure");
				
				j++;
			}
		}
		
		pressureavg = pressure/j;
		visibilityavg = visibility/j;
		
		visibility = 0;
		pressure = 0;
		
		for(int i = 0; i<stats.size(); i++)
		{
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			if(pressureobj.get("date").toString().substring(0, 10).equals(day))
			{
				visibility += Math.pow((((long) pressureobj.get("visibility")) - visibilityavg), 2);
				pressure += Math.pow((((long) pressureobj.get("pressure")) - pressureavg), 2);
			}
		}
		
		pressurevar = pressure/j;
		visibilityvar = visibility/j;
		
		object.put("city", city);
		object.put("date", day);
		
		average.put("pressureAvg", pressureavg);
		average.put("visibilityAvg", visibilityavg);
		
		variance.put("pressureVariance", pressurevar);
		variance.put("visibilityVariance", visibilityvar);
		
		maxmin.put("pressureMax", pressuremax);
		maxmin.put("pressureMin", pressuremin);
		maxmin.put("visibilityMax", visibilitymax);
		maxmin.put("visibilityMin", visibilitymin);
		
		object.put("average", average);
		object.put("variance", variance);
		object.put("maxMin", maxmin);
		
		return object;
	}
}

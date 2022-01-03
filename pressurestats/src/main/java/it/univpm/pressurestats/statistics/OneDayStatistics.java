package it.univpm.pressurestats.statistics;

import org.json.simple.JSONObject;


public class OneDayStatistics extends Statistics{
	
	public OneDayStatistics(String city, String day)
	{
		super(city, day);
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject OneDay(String city, String day)
	{
		JSONObject object = new JSONObject();
		
		object.put("city", city);
		object.put("date", day);
		
		object.put("average", avg());
		object.put("variance", variance());
		object.put("maxMin", MaxMin());
		
		return object;
	}
}

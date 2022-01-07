package it.univpm.pressurestats.statistics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;

/**
 * Questa classe permette di calcolare le statistiche.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
 
public class Statistics {
	
	private JSONArray stats;
	private Filters od;
	private String city;
	private String date;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param day giorno di cui calcolare statistiche
	 */
	public Statistics(String city, String day)
	{
		od = new Filters();
		stats = od.oneDayWeather(city, day);
		this.date = day;
		this.city = city;
	}
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param day giorno di cui calcolare statistiche
	 *	@param from ora da cui partire
	 *	@param to ora a cui arrivare
	 * @throws WrongHoursPeriodException 
	 * @throws CityStatisticsNotFoundException 
	 * @throws DayNotFoundException 
	 */	
	public Statistics(String city, String day, int from, int to) throws WrongHoursPeriodException, CityStatisticsNotFoundException, DayNotFoundException
	{
		od = new Filters();
		stats = od.hourly(city, day, from, to);
		this.date = day;
		this.city = city;
	}
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param numDays numero di giorni di cui calcolare statistiche
	 */
	public Statistics(String city, int numDays)
	{
		od = new Filters();
		stats = od.moreDayWeather(city, numDays);
		this.city = city;
	}
	
	/**
	 * Metodo che calcola statistiche.
	 * 
	 * 
	 * 
	 * @return Un JSONObject contenente statistiche
	 */
	@SuppressWarnings("unchecked")
	public JSONObject stats()
	{
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

		for (int i = 0; i < stats.size(); i++) {
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);

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

		
		pressureavg = pressure/j;
		visibilityavg = visibility/j;
		
		visibility = 0;
		pressure = 0;
		
		for(int i = 0; i<stats.size(); i++)
		{
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			visibility += Math.pow((((long) pressureobj.get("visibility")) - visibilityavg), 2);
			pressure += Math.pow((((long) pressureobj.get("pressure")) - pressureavg), 2);
		}
		
		pressurevar = pressure/j;
		visibilityvar = visibility/j;
		
		object.put("city", city);
		object.put("date", date);
		
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

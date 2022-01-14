package it.univpm.pressurestats.statistics;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;

/**
 * Permette di calcolare e salvare le statistiche.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
 
public class Statistics{
	/**
	 * JSONArray in cui vengono salvate le statistiche
	 */
	private JSONArray stats;
	/**
	 * Permette di usare i filtri e le loro operazioni.
	 */
	private Filters od;
	/**
	 * Nome città di cui calcolare statistiche.
	 */
	private String city;
	/**
	 * Data di cui calcolare statistiche.
	 */
	private String date;
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city città di cui calcolare statitische
	 * @param day giorno di cui calcolare statistiche
	 * @throws DayNotFoundException eccezione lanciata se il giorno non è stato trovato
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche della città non sono state trovate
	 */
	public Statistics(String city, String day) throws DayNotFoundException, CityStatisticsNotFoundException
	{
		od = new Filters();
		stats = od.oneDayWeather(city, day);
		this.date = day;
		this.city = city;
	}
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city città di cui calcolare statitische
	 * @param day giorno di cui calcolare statistiche
	 *	@param from ora da cui partire
	 *	@param to ora a cui arrivare
	 * @throws WrongHoursPeriodException eccezione lanciata se il range orario non è corretto
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche della città non sono state trovate.
	 * @throws DayNotFoundException eccezione lanciata se il giorno non è stato trovato
	 */	
	public Statistics(String city, String day, int from, int to) throws WrongHoursPeriodException, CityStatisticsNotFoundException, DayNotFoundException
	{
		od = new Filters();
		stats = od.hourly(city, day, from, to);
		this.date = day + " from " + from + ":00 to " + to + ":00";
		this.city = city;
	}
	
	/**
	 * Costruttore della classe.
	 * 
	 * @param city città di cui calcolare statitische
	 * @param numDays numero di giorni di cui calcolare statistiche
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche della città non sono state trovate.
	 */
	public Statistics(String city, int numDays) throws CityStatisticsNotFoundException
	{
		od = new Filters();
		stats = od.moreDayWeather(city, numDays);
		this.city = city;
		Calendar cal = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal.add(Calendar.DATE, -numDays);
		cal2.add(Calendar.DATE, -0);
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		this.date = "From " + dateFormat.format(cal.getTime()) + " to " + dateFormat.format(cal2.getTime());
	}
	
	/**
	 * Calcola le statistiche.
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
	/**
	 * Salva le statistiche calcolate su un file di testo.
	 * 
	 * @param object JSONObject contenente i dati da salvare
	 * 
	 * 
	 */
	public void saveToFile(JSONObject object) {

		String fileName = "";
		
		if (this.date.charAt(0) == 'F') {
			fileName = this.city+ "_stats_MultiDays";
		}
		else {
			fileName = this.city+ "_stats_"+this.date.substring(0,10);
		}
		String path = System.getProperty("user.dir") + "/src/main/resources/" + fileName + ".txt";
	
		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
			pw.append(object.toJSONString() + "\n");
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	
}

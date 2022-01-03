package it.univpm.pressurestats.statistics;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.pressurestats.service.ServiceImpl;

/**
 * Questa classe permette di calcolare le statistiche.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public class Statistics {
	ServiceImpl od = new ServiceImpl();

	private JSONArray stats = new JSONArray();
	private JSONArray vispre = new JSONArray();

	private JSONObject weather = new JSONObject();
	private JSONObject pressureobj = new JSONObject();

	private long pressureavg;
	private long visibilityavg;

	private String day;

	/**
	 * Costruttore della classe.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param date giorno di cui calcolare statistiche
	 */
	public Statistics(String city, String date) {
		this.stats = od.readFile(city, date);
		this.vispre = new JSONArray();
		this.weather = new JSONObject();
		this.pressureobj = new JSONObject();
		this.pressureavg = 0;
		this.visibilityavg = 0;
		this.day = date;
	}

	/**
	 * Questo metodo calcola la media tra i vari dati di pressione e visibilita'
	 * 
	 * @return La media dei vari dati di pressione e visibilita'
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject avg() {
		JSONObject average = new JSONObject();

		long visibility = 0;
		long pressure = 0;

		int j = 0;

		for (int i = 0; i < stats.size(); i++) {
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			if (pressureobj.get("date").toString().substring(0, 10).equals(day)) {
				visibility += (long) pressureobj.get("visibility");
				pressure += (long) pressureobj.get("pressure");
				j++;
			}
		}

		pressureavg = pressure / j;
		visibilityavg = visibility / j;

		average.put("pressureAvg", pressureavg);
		average.put("visibilityAvg", visibilityavg);

		return average;
	}

	/**
	 * Questo metodo calcola il massimo e il minimo tra i vari dati di pressione e
	 * visibilita'
	 * 
	 * @return il massimo e il minimo dei vari dati di pressione e visibilita'
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject MaxMin() {
		JSONObject maxMin = new JSONObject();

		long visibilitymin = 0;
		long visibilitymax = 0;
		long pressuremax = 0;
		long pressuremin = 0;

		int j = 0;

		for (int i = 0; i < stats.size(); i++) {
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			if (pressureobj.get("date").toString().substring(0, 10).equals(day)) {
				if (j == 0) {
					pressuremax = (long) pressureobj.get("pressure");
					pressuremin = (long) pressureobj.get("pressure");
					visibilitymax = (long) pressureobj.get("visibility");
					visibilitymin = (long) pressureobj.get("visibility");
				}

				if (visibilitymax < (long) pressureobj.get("visibility"))
					visibilitymax = (long) pressureobj.get("visibility");
				else if (visibilitymin > (long) pressureobj.get("visibility"))
					visibilitymin = (long) pressureobj.get("visibility");

				if (pressuremax < (long) pressureobj.get("pressure"))
					pressuremax = (long) pressureobj.get("pressure");
				else if (pressuremin > (long) pressureobj.get("pressure"))
					pressuremin = (long) pressureobj.get("pressure");

				j++;
			}
		}

		maxMin.put("pressureMax", pressuremax);
		maxMin.put("pressureMin", pressuremin);
		maxMin.put("visibilityMax", visibilitymax);
		maxMin.put("visibilityMin", visibilitymin);

		return maxMin;
	}

	/**
	 * Questo metodo calcola la varianza tra i vari dati di pressione e visibilita'
	 * 
	 * @return la varianza dei vari dati di pressione e visibilita'
	 * 
	 */
	@SuppressWarnings("unchecked")
	public JSONObject variance() {
		JSONObject variance = new JSONObject();

		long visibility = 0;
		long pressure = 0;
		long pressurevar = 0;
		long visibilityvar = 0;

		int j = 0;

		for (int i = 0; i < stats.size(); i++) {
			weather = (JSONObject) stats.get(i);
			vispre = (JSONArray) weather.get("weather");
			pressureobj = (JSONObject) vispre.get(0);
			if (pressureobj.get("date").toString().substring(0, 10).equals(day)) {
				visibility += Math.pow((((long) pressureobj.get("visibility")) - visibilityavg), 2);
				pressure += Math.pow((((long) pressureobj.get("pressure")) - pressureavg), 2);
				j++;
			}
		}

		pressurevar = pressure / j;
		visibilityvar = visibility / j;

		variance.put("pressureVariance", pressurevar);
		variance.put("visibilityVariance", visibilityvar);

		return variance;
	}
}

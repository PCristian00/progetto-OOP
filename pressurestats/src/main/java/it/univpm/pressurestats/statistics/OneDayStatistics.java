package it.univpm.pressurestats.statistics;

import org.json.simple.JSONObject;

/**
 * Questa classe permette di calcolare le statistiche per un giorno.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public class OneDayStatistics extends Statistics {

	/**
	 * Costruttore della classe.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param day  giorno di cui calcolare statistiche
	 */
	public OneDayStatistics(String city, String day) {
		super(city, day);
	}

	/**
	 * Questo metodo calcola le statistiche per un giorno di una citta'.
	 * 
	 * @param city citta' di cui calcolare statitische
	 * @param day  giorno di cui calcolare statistiche
	 * @return un JSONObject contenente le statistiche per un giorno di una citta'.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject OneDay(String city, String day) {
		JSONObject object = new JSONObject();

		object.put("city", city);
		object.put("date", day);

		object.put("average", avg());
		object.put("variance", variance());
		object.put("maxMin", MaxMin());

		return object;
	}
}

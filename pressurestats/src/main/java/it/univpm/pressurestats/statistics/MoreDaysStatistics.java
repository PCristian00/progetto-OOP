package it.univpm.pressurestats.statistics;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONObject;

/**
 * Questa classe permette di calcolare le statistiche per piu' giorni.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public class MoreDaysStatistics {

	OneDayStatistics od;

	/**
	 * Questo metodo calcola le statistiche per piu' giorni di una citta'.
	 * 
	 * @param city    citta' di cui calcolare statitische
	 * @param numDays numero di giorni di cui calcolare statistiche
	 * @return un JSONObject contenente le statistiche per piu' giorni di una
	 *         citta'.
	 */
	@SuppressWarnings("unchecked")
	public JSONObject MoreDays(String city, int numDays) {
		JSONObject statsMoreDays = new JSONObject();

		for (int i = 0; i < numDays; i++) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -i);
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			String day = df.format(cal.getTime());

			od = new OneDayStatistics(city, day);

			statsMoreDays.put(day, od.OneDay(city, day));
		}

		return statsMoreDays;
	}
}

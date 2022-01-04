package it.univpm.pressurestats.statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

/**
 * Questa classe gestisce i filtri.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public class Filters {

	/**
	 * Questo metodo prepara un JSONArray delle statistiche di un solo giorno di una citta'.
	 * 
	 * @param city citta' scelta
	 * @param day  giorno di cui filtrare statistiche
	 * @return Le statistiche di un giorno in un JSONArray.
	 */
	@SuppressWarnings("unchecked")
	public JSONArray oneDayWeather(String city, String day) {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = System.getProperty("user.dir") + city + ".txt";

		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				if (data.contains(day))
					ja.add((JSONObject) JSONValue.parseWithException(data));
			buff.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return ja;
	}

	/**
	 * Questo metodo prepara un JSONArray delle statistiche di piu' giorni.
	 * 
	 * @param city    citta' scelta
	 * @param numDays numeri di giorni di cui filtrare statistiche
	 * @return Le statistiche di piu' giorni in un JSONArray.
	 */
	@SuppressWarnings("unchecked")
	public JSONArray moreDayWeather(String city, int numDays) {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = System.getProperty("user.dir") + city + ".txt";
		String day;

		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				for (int i = 0; i < numDays; i++) {
					Calendar cal = Calendar.getInstance();
					cal.add(Calendar.DATE, -i);
					DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					day = dateFormat.format(cal.getTime());
					if (data.contains(day))
						ja.add((JSONObject) JSONValue.parseWithException(data));
				}
			buff.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return ja;
	}

	/**
	 * Questo metodo prepara un JSONArray delle statistiche orarie di un giorno di
	 * una citta'.
	 * 
	 * @param city citta' scelta
	 * @param day  giorno di cui filtrare statistiche
	 * @param from prima ora
	 * @param to   ultima ora
	 * @return Le statistiche di piu' ore in un JSONArray.
	 */
	@SuppressWarnings("unchecked")
	public JSONArray hourly(String city, String day, int from, int to) {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = System.getProperty("user.dir") + city + ".txt";
		String hour;

		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				if (data.contains(day)) {
					hour = data.substring(data.indexOf(day) + 11, data.indexOf(day) + 13);
					if (Integer.parseInt(hour) >= from && Integer.parseInt(hour) <= to)
						ja.add((JSONObject) JSONValue.parseWithException(data));
				}
			buff.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return ja;
	}
}

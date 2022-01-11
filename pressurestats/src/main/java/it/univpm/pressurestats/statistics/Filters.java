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


import it.univpm.pressurestats.service.ServiceImpl;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;


/**
 * Gestisce i filtri.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public class Filters {
/**
 * Costruttore della classe.
 */
	public Filters() {
		
	}
	/**
	 * Prepara un JSONArray delle statistiche di un solo giorno di una città.
	 * 
	 * @param city città scelta
	 * @param day  giorno di cui filtrare statistiche
	 * @return Le statistiche di un giorno in un JSONArray.
	 * @throws DayNotFoundException            eccezione lanciata se il giorno non è
	 *                                         stato trovato
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche
	 *                                         della città non sono state trovate
	 */
	@SuppressWarnings("unchecked")
	public JSONArray oneDayWeather(String city, String day)
			throws DayNotFoundException, CityStatisticsNotFoundException {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = ServiceImpl.dir + city + ServiceImpl.f_type;
		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				if (data.contains(day))
					ja.add((JSONObject) JSONValue.parseWithException(data));
			buff.close();
			if (ja.size() == 0)
				throw new DayNotFoundException("Giorno non presente nelle statistiche");
		} catch (IOException e) {
			throw new CityStatisticsNotFoundException("Città non presente nelle statistiche");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return ja;
	}

	/**
	 * Prepara un JSONArray delle statistiche di più giorni.
	 * 
	 * @param city    città scelta
	 * @param numDays numeri di giorni di cui filtrare statistiche
	 * @return Le statistiche di più giorni in un JSONArray.
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche
	 *                                         della città non sono state trovate
	 */
	@SuppressWarnings("unchecked")
	public JSONArray moreDayWeather(String city, int numDays) throws CityStatisticsNotFoundException {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = ServiceImpl.dir + city + ServiceImpl.f_type;
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
		} catch (IOException e) {
			throw new CityStatisticsNotFoundException("Città non presente nelle statistiche");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return ja;
	}

	/**
	 * Prepara un JSONArray delle statistiche orarie di un giorno di una città.
	 * 
	 * @param city città scelta
	 * @param day  giorno di cui filtrare statistiche
	 * @param from prima ora
	 * @param to   ultima ora
	 * @return Le statistiche di più ore in un JSONArray.
	 * @throws WrongHoursPeriodException       eccezione lanciata se il range orario
	 *                                         non è corretto
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche
	 *                                         della città non sono state trovate
	 * @throws DayNotFoundException            eccezione lanciata se il giorno non è
	 *                                         stato trovato
	 */
	@SuppressWarnings("unchecked")
	public JSONArray hourly(String city, String day, int from, int to)
			throws WrongHoursPeriodException, CityStatisticsNotFoundException, DayNotFoundException {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = ServiceImpl.dir + city + ServiceImpl.f_type;
		String hour;

		if (from < 0 || to > 24 || from > to)
			throw new WrongHoursPeriodException("Inserire un periodo orario corretto (0-24)");

		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				if (data.contains(day)) {
					hour = data.substring(data.indexOf(day) + 11, data.indexOf(day) + 13);
					if (Integer.parseInt(hour) >= from && Integer.parseInt(hour) <= to)
						ja.add((JSONObject) JSONValue.parseWithException(data));
				}
			buff.close();
			if (ja.size() == 0) throw new DayNotFoundException("Giorno non presente nelle statistiche");
		} catch (IOException e) {
			throw new CityStatisticsNotFoundException("Città non presente nelle statistiche");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return ja;
	}
}

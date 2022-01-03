package it.univpm.pressurestats.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import it.univpm.pressurestats.model.City;

/**
 * Questa classe è l'interfaccia di ServiceImpl e contiene i metodi richiamati
 * dal Controller.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public interface Service {

	/**
	 * COMPLETARE
	 * 
	 * @param city citta' di cui si vogliono sapere le previsioni
	 * @return Un JSONObject (forecast) contenente le previsioni
	 */
	public abstract JSONObject toJSON(City city);
	/**
	 * Questo metodo va a prendere da OpenWeather le previsioni meteo di una citta'.
	 * 
	 * @param name       nome della citta' da cercare
	 * @param isObject COMPLETARE
	 * @return un JSONObject contenente le previsioni meteo complete.
	 */
	public abstract JSONObject getJSONForecast(String name, boolean isObject);
	/**
	 * Questo metodo utilizza getJSONForecast per prendere le informazioni di
	 * visibilita' e pressione utilizzate per le statistiche.
	 * 
	 * @param forecast JSONObject contenente le previsioni restituito da
	 *            getJSONForecast(String id, boolean isObject)
	 * @return un oggetto di tipo City che contiene tutte le informazioni richieste
	 *         e anche le informazioni sulla citta'.
	 */
	public abstract City getForecast(JSONObject forecast);
	/**
	 * Questo metodo richiama getForecast(JSONObject obj) e salva i dati su un file
	 * di testo (salvato in /src/main/resources/)
	 * 
	 * @param obj JSONObject contenente le previsioni restituito da
	 *               getJSONForecast(String id, boolean isObject)
	 */
	public abstract void saveToFile(JSONObject obj);
	/**
	 * Questo metodo salva ogni ora su un file i dati delle previsioni utili
	 * richiamando getJSONForecast(String id, boolean isObject) e
	 * saveToFile(JSONObject object)
	 * 
	 * @param id id della citta' di cui salvare i dati ogni ora
	 */
//TODO aggiunta nuova operazione, valutare cambio di argomento
	public abstract void saveToFileHourly(String id);
	
	public abstract JSONArray readFile(String city, String day);
}

package it.univpm.pressurestats.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.model.City;

/**
 * Questa è l'interfaccia contenente i metodi implementati da ServiceImpl e richiamati
 * dal Controller.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
public interface Service {
	/**
	 * Converte l'oggetto di tipo City in un JSONObject. 
	 * 
	 * @param city città di cui si vogliono sapere le previsioni
	 * @return Un JSONObject (forecast) contenente le previsioni
	 */
	public abstract JSONObject toJSON(City city);

	/**
	 * Scarica da OpenWeather le previsioni meteo di una città data..
	 * 
	 * @param name     nome della città da cercare
	 * @param isObject controllo di tipo Boolean (COMPLETARE)
	 * @return un JSONObject contenente le previsioni meteo complete.
	 */
	public abstract JSONObject getJSONForecast(String name, boolean isObject);

	/**
	 * Utilizza getJSONForecast per prendere le informazioni di
	 * visibilità e pressione utilizzate per le statistiche.
	 * 
	 * @param forecast JSONObject contenente le previsioni restituito da
	 *                 getJSONForecast(String id, boolean isObject)
	 * @return un oggetto di tipo City che contiene tutte le informazioni richieste
	 *         e anche le informazioni sulla città.
	 *         @throws ItalianCityNotFoundException eccezione lanciata se la città non è italiana
	 */
	public abstract City getForecast(JSONObject forecast) throws ItalianCityNotFoundException;

	/**
	 * Richiama getForecast(JSONObject obj) e salva i dati su un file
	 * di testo (salvato in /src/main/resources/)
	 * 
	 * @param obj JSONObject contenente le previsioni restituito da
	 *            getJSONForecast(String id, boolean isObject)
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è italiana
	 */
	public abstract void saveToFile(JSONObject obj) throws ItalianCityNotFoundException;

	/**
	 * Salva ogni ora su un file i dati delle previsioni utili
	 * richiamando getJSONForecast(String id, boolean isObject) e
	 * saveToFile(JSONObject object)
	 * 
	 * @param id id della città di cui salvare i dati ogni ora
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è italiana
	 */
//TODO aggiunta nuova operazione, valutare cambio di argomento
	public abstract void saveToFileHourly(String id) throws ItalianCityNotFoundException;

	/**
	 * Legge dati da un file salvato in precedenza
	 * 
	 * @param city città di cui leggere i dati
	 * @return JSONArray contenente i dati letti
	 */
	public abstract JSONArray readFile(String city);
}

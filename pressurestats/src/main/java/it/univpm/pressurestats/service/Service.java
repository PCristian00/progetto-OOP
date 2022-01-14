package it.univpm.pressurestats.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.pressurestats.exception.IdNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.NegativeStartException;
import it.univpm.pressurestats.exception.WrongMultiplyException;
import it.univpm.pressurestats.model.City;

/**
 * Interfaccia contenente i metodi implementati da ServiceImpl e richiamati dal
 * Controller.
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
	 * @throws IdNotFoundException eccezione lanciata se non è stato trovato nessun
	 *                             ID corrispondente alla richiesta
	 */
	public abstract JSONObject getJSONForecast(String name, boolean isObject) throws IdNotFoundException;

	/**
	 * Utilizza getJSONForecast per prendere le informazioni di visibilità e
	 * pressione utilizzate per le statistiche.
	 * 
	 * @param forecast JSONObject contenente le previsioni restituito da
	 *                 getJSONForecast(String id, boolean isObject)
	 * @return un oggetto di tipo City che contiene tutte le informazioni richieste
	 *         e anche le informazioni sulla città.
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 */
	public abstract City getForecast(JSONObject forecast) throws ItalianCityNotFoundException;

	/**
	 * Richiama getForecast(JSONObject obj) e salva i dati su un file di testo
	 * (salvato in /src/main/resources/)
	 * 
	 * @param obj JSONObject contenente le previsioni restituito da
	 *            getJSONForecast(String id, boolean isObject)
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 */
	public abstract void saveToFile(JSONObject obj) throws ItalianCityNotFoundException;

	/**
	 * Salva ogni tot ore su un file i dati delle previsioni utili richiamando
	 * getJSONForecast(String id, boolean isObject) e saveToFile(JSONObject object)
	 * 
	 * @param id         id della città di cui salvare i dati ogni tot ore
	 * @param multiplier moltiplicatore dell'ora per cambiare la frequenza di
	 *                   salvataggio. (Es. Impostando il valore a 2, il salvataggio
	 *                   avviene ogni 2 ore, a 0.5 ogni 30 minuti).
	 * @param start      millisecondo di partenza del salvataggio
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 * @throws WrongMultiplyException       eccezione lanciata se il moltiplicatore
	 *                                      non è ammesso (moltiplicatore minore o
	 *                                      uguale a 0.02).Un moltiplicatore di 0.02
	 *                                      restituirebbe dati ogni minuto circa.
	 * @throws NegativeStartException       eccezione lanciata se il valore di Start
	 *                                      è inferiore a 0. (Negativo)
	 * @throws IdNotFoundException          eccezione lanciata se non è stato
	 *                                      trovato nessun ID corrispondente alla
	 *                                      richiesta
	 */
	public abstract void saveToFileHourly(String id, double multiplier, long start)
			throws ItalianCityNotFoundException, WrongMultiplyException, NegativeStartException, IdNotFoundException;

	/**
	 * Legge dati da un file salvato in precedenza
	 * 
	 * @param city città di cui leggere i dati
	 * @return JSONArray contenente i dati letti
	 */
	public abstract JSONArray readFile(String city);

	/**
	 * A partire dal multiplier, prepara una stringa contenente la frequenza di
	 * aggiornamento del salvataggio.
	 * 
	 * @param multiplier moltiplicatore dell'ora per cambiare la frequenza di
	 *                   salvataggio. (Es. Impostando il valore a 2, il salvataggio
	 *                   avviene ogni 2 ore, a 0.5 ogni 30 minuti).
	 * @return un messaggio contenente la frequenza di aggiornamento
	 */
	public String saveMessage(double multiplier);
}

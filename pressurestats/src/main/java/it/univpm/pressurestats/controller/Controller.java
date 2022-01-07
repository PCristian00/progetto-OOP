package it.univpm.pressurestats.controller;

import javax.naming.event.NamespaceChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.service.Service;
import it.univpm.pressurestats.statistics.Statistics;

/**
 * La classe Controller processa le varie richieste, prepara il modello e
 * restituisce una risposta.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */

@RestController
public class Controller {
	@Autowired
	Service service;
	Statistics statistics;	

	/**
	 * Rotta di tipo GET che mostra le informazioni attuali su pressione e
	 * visibilita'. I dati vengono stampati su schermo e salvati in un file di
	 * testo.
	 * 
	 * @param id rappresenta la citta' di cui si richiedono le previsioni. Valore di default: 3169070 (Rome,IT)
	 * @return le previsioni meteo su pressione e visibilità della città richiesta e
	 *         le informazioni generali sulla citta'.
	 * @throws ItalianCityNotFoundException 
	 */

	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3169070") String id) throws ItalianCityNotFoundException {
		// TODO Portata all'esterno il metodo saveToFile per evitare ripetizioni in
		// saveToFileHourly (vedere getJSONForecast)
		try {
			service.saveToFile((service.getJSONForecast(id, true)));
			return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
		} catch (ItalianCityNotFoundException e1){
			return new ResponseEntity<>(e1.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Rotta di tipo GET che, se lasciata in esecuzione, salva ogni ora le
	 * informazioni attuali su pressione e visibilita'
	 *
	 * @param id rappresenta la citta' di cui si richiedono le previsioni.  Valore di default: 3169070 (Rome,IT)
	 * @return "Il salvataggio avverra' ogni ora, lasciare programma in esecuzione."
	 * @throws ItalianCityNotFoundException 
	 */
	@GetMapping(value = "/hourlySave")
	public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "id", defaultValue = "3169070") String id){
			try {
				service.saveToFileHourly(id);

				// TODO trovare messaggio migliore o fare return di ResponseEntity (vedi
				// /current)
				return new ResponseEntity<>("Il salvataggio avverrà ogni ora, lasciare programma in esecuzione.", HttpStatus.OK);
			} catch (ItalianCityNotFoundException e) {
				// TODO Auto-generated catch block
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
	}
	

	/**
	 * Rotta di tipo GET che, restituisce le statistiche giornaliere di una citta'.
	 * 
	 * @param city rappresenta la citta' di cui si richiedono le statistiche.
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @return Le statistiche per un giorno
	 */
	
	@GetMapping(value="/oneDay")
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Rome") String city,

	@RequestParam(name = "date") String date)
	{
		try {
			statistics = new Statistics(city, date);
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException | DayNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Rotta di tipo GET che, restituisce le statistiche per piu' giorni di una
	 * citta'.
	 * 
	 * @param city rappresenta la citta' di cui si richiedono le statistiche.
	 * @param days Il numero di giorni di cui si vogliono ricevere statistiche
	 * @return Le statistiche per piu' giorni
	 */
	
	@GetMapping(value="/moreDays")
	public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Rome") String city,
												@RequestParam(name = "days") int days)
	{
		try {
			statistics = new Statistics(city, days);
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Rotta di tipo GET che, restituisce le statistiche per piu' ore di un giorno di una
	 * citta'.
	 * 
	 * @param city rappresenta la citta' di cui si richiedono le statistiche.
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @param from prima ora
	 * @param to ultima ora
	 * @return Le statistiche per piu' ore
	 * @throws CityStatisticsNotFoundException 
	 */
	@GetMapping(value = "/hourly")
	public ResponseEntity<Object> getStatisticsHourly(
			@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "date") String date, @RequestParam(name = "from") int from,
			@RequestParam(name = "to") int to){
		try {
			statistics = new Statistics(city, date, from, to);
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (WrongHoursPeriodException | CityStatisticsNotFoundException | DayNotFoundException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

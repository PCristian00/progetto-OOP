package it.univpm.pressurestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	
	OneDayStatistics statistics;
	MoreDaysStatistics more = new MoreDaysStatistics();

	/**
	 * Rotta di tipo GET che mostra le informazioni attuali su pressione e
	 * visibilita'. I dati vengono stampati su schermo e salvati in un file di
	 * testo.
	 * 
	 * @param id rappresenta la citta' di cui si richiedono le previsioni.
	 * @return le previsioni meteo su pressione e visibilità della città richiesta e
	 *         le informazioni generali sulla citta'.
	 */

	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3173006") String id) {
		// TODO Portata all'esterno il metodo saveToFile per evitare ripetizioni in
		// saveToFileHourly (vedere getJSONForecast)
		service.saveToFile((service.getJSONForecast(id, true)));

		return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
	}

	/**
	 * Rotta di tipo GET che, se lasciata in esecuzione, salva ogni ora le
	 * informazioni attuali su pressione e visibilita'
	 * 
	 * @param id rappresenta la citta' di cui si richiedono le previsioni.
	 * @return "Il salvataggio avverra' ogni ora, lasciare programma in esecuzione."
	 */
	@GetMapping(value = "/hourlySave")
	public String saveToFileHourly(@RequestParam(name = "id", defaultValue = "3173006") String id) {
		service.saveToFileHourly(id);

		// TODO trovare messaggio migliore o fare return di ResponseEntity (vedi
		// /current)
		return "Il salvataggio avverrà ogni ora, lasciare programma in esecuzione.";
	}
	

	/**
	 * Rotta di tipo GET che, restituisce le statistiche giornaliere di una citta'.
	 * 
	 * @param city rappresenta la citta' di cui si richiedono le statistiche.
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @return Le statistiche per un giorno
	 */
	
	@GetMapping(value="/oneDay")
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,

	@RequestParam(name = "date") String date)
	{
		statistics = new Statistics(city, date);
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
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
	public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,
												@RequestParam(name = "days") int days)
	{
		statistics = new Statistics(city, days);
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
	}
	
	@GetMapping(value="/hourly")
	public ResponseEntity<Object> getStatisticsHourly(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,
												@RequestParam(name = "date") String date,
												@RequestParam(name = "from") int from,
												@RequestParam(name = "to") int to)
	{
		statistics = new Statistics(city, date, from, to);
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
	}
}

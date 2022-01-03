package it.univpm.pressurestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.pressurestats.service.Service;
import it.univpm.pressurestats.statistics.MoreDaysStatistics;
import it.univpm.pressurestats.statistics.OneDayStatistics;

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
	@GetMapping(value = "/hourly")
	public String saveToFileHourly(@RequestParam(name = "id", defaultValue = "3173006") String id) {
		service.saveToFileHourly(id);

		// TODO trovare messaggio migliore o fare return di ResponseEntity (vedi
		// /current)
		return "Il salvataggio avverrà ogni ora, lasciare programma in esecuzione.";


	
	
		
	@GetMapping(value="/stats")
	public ResponseEntity<Object> getStatistics(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,
												@RequestParam(name = "date") String date)
	{
		statistics = new OneDayStatistics(city, date);
		return new ResponseEntity<>(statistics.OneDay(city, date), HttpStatus.OK);
	}
	
	@GetMapping(value = "/stats/days")
	public ResponseEntity<Object> getMoreDaysStatistics(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,
														@RequestParam(name = "days", defaultValue = "1") int days)
	{
		return new ResponseEntity<>(more.MoreDays(city, days), HttpStatus.OK);

	}
}

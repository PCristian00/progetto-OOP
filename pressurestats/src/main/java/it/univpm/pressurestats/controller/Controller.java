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
	Statistics statistics;	

	/**
	 * Rotta di tipo GET che mostra le informazioni attuali su pressione e
	 * visibilita'. I dati vengono stampati su schermo e salvati in un file di
	 * testo.
	 * 
	 * @param id rappresenta la citta' di cui si richiedono le previsioni. Valore di default: 3169070 (Rome,IT)
	 * @return le previsioni meteo su pressione e visibilità della città richiesta e
	 *         le informazioni generali sulla citta'.
	 */

	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3169070") String id) {
		// TODO Portata all'esterno il metodo saveToFile per evitare ripetizioni in
		// saveToFileHourly (vedere getJSONForecast)
		service.saveToFile((service.getJSONForecast(id, true)));

		return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
	}
	
	//TODO ROTTA BOZZA:Togliere da programma finale o rimediare
			//TODO A volte questa rotta chiama contemporaneamente per due città diverse e inserisce i dati nel file sbagliato
			//TODO ricontrollare SEMPRE i dati ottenuti dopo l'utilizzo
	@GetMapping(value = "/multiSave")
	public String saveToFileHourly() {
		
		
		//ROMA
		service.saveToFileHourly("3169070");
		
		//NAPOLI
		service.saveToFileHourly("3172395");
				
		//MILANO
		service.saveToFileHourly("6542283");
				
		//ANCONA
		service.saveToFileHourly("6542126");
				
		//PALERMO
		service.saveToFileHourly("2523920");

		
		return "Raccolta oraria molteplici dati, ricontrollare file finali, lasciare in esecuzione applicazione.";
	}

	/**
	 * Rotta di tipo GET che, se lasciata in esecuzione, salva ogni ora le
	 * informazioni attuali su pressione e visibilita'
	 *
	 * @param id rappresenta la citta' di cui si richiedono le previsioni.  Valore di default: 3169070 (Rome,IT)
	 * @return "Il salvataggio avverra' ogni ora, lasciare programma in esecuzione."
	 */
	@GetMapping(value = "/hourlySave")
	public String saveToFileHourly(@RequestParam(name = "id", defaultValue = "3169070") String id) {
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
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Rome") String city,

	@RequestParam(name = "date") String date)
	{
		statistics = new Statistics(city, date);
		//TODO aggiunto salvataggio (Funzionante)
		statistics.saveToFile(statistics.stats());
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
	public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Rome") String city,
												@RequestParam(name = "days") int days)
	{
		statistics = new Statistics(city, days);
		//TODO aggiunto salvataggio (Funzionante)
				statistics.saveToFile(statistics.stats());
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
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
	 */
	@GetMapping(value = "/hourly")
	public ResponseEntity<Object> getStatisticsHourly(
			@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "date") String date, @RequestParam(name = "from") int from,
			@RequestParam(name = "to") int to) {
		statistics = new Statistics(city, date, from, to);
		//TODO aggiunto salvataggio (Funzionante)
				statistics.saveToFile(statistics.stats());
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
	}
}

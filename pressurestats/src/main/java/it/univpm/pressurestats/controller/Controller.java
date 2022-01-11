package it.univpm.pressurestats.controller;

//import javax.naming.event.NamespaceChangeListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.IdNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.exception.WrongMultiplyException;
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

	/**
	 * Costruttore dell'oggetto.
	 */	
	public Controller() {
		
	}
	
	/**
	 * Interfaccia Service usata per le operazioni di raccolta e salvataggio dati.
	 */
	@Autowired
	Service service;
	/**
	 * Oggetto Statitstics usata per le operazioni di generazione e salvataggio statistiche.
	 */
	Statistics statistics;
	
	/**
	 * Mostra le informazioni attuali su pressione e
	 * visibilità. I dati vengono stampati su schermo e salvati in un file di
	 * testo.
	 * 
	 * @param id rappresenta la città di cui si richiedono le previsioni. Valore di default: 3169070 (Rome,IT)
	 * @return le previsioni meteo su pressione e visibilità della città richiesta e
	 *         le informazioni generali sulla città.
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è italiana
	 * @throws IdNotFoundException eccezione lanciata se non è stato trovato nessun ID corrispondente alla richiesta
	 */
	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3169070") String id) throws ItalianCityNotFoundException, IdNotFoundException {
		// TODO Portata all'esterno il metodo saveToFile per evitare ripetizioni in
		// saveToFileHourly (vedere getJSONForecast)
		
		try {
			service.saveToFile((service.getJSONForecast(id, true)));
			return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
		} catch (IdNotFoundException | ItalianCityNotFoundException e1){
			return new ResponseEntity<>(e1.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//TODO ROTTA BOZZA:Togliere da programma finale o rimediare
		//TODO A volte questa rotta chiama contemporaneamente per due città diverse e inserisce i dati nel file sbagliato (Aggiunge righe vuote)
			//TODO ricontrollare SEMPRE i dati ottenuti dopo l'utilizzo
	
	/**
	 * Se lasciata in esecuzione, salva con una frequenza impostata dall'utente le
	 * informazioni attuali su pressione e visibilita' di 5 città non scelte dall'utente.
	 *
	 *Questa rotta presenta ancora problemi, ricontrollare il risultato finale.
	 * 
	 * @param multiplier moltiplicatore dell'ora (Esempio: 0,5 salva i dati ogni 30
	 *                   minuti, 2 ogni 2 ore)
	 * @return Un messaggio di riepilogo con la frequenza
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è italiana
	 * @throws WrongMultiplyException eccezione lanciata se il moltiplicatore non è ammesso (moltiplicatore minore o uguale a 0.02).Un moltiplicatore di 0.02 restituirebbe dati ogni minuto circa
	 */
	@GetMapping(value = "/multiSave")
	public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "multiplier", defaultValue = "1") double multiplier) throws ItalianCityNotFoundException, WrongMultiplyException{		
		try {
		//ROMA		
			service.saveToFileHourly("3169070",multiplier);			
		//NAPOLI		
			service.saveToFileHourly("3172395",multiplier);	
		//MILANO
			service.saveToFileHourly("6542283",multiplier);				
		//ANCONA		
			service.saveToFileHourly("6542126",multiplier);			
		//PALERMO		
			service.saveToFileHourly("2523920",multiplier);
			/*
			String msg;
			if (multiplier != 1)
				msg =((int) Math.round(multiplier*60)) + " minuti circa";
			else
				msg = "ora";
				*/
		return new ResponseEntity<>(service.saveMessage(multiplier)+ "Ricontrollare file finali, lasciare in esecuzione applicazione.",HttpStatus.OK);
		}catch(ItalianCityNotFoundException | WrongMultiplyException e2) {
			return new ResponseEntity<>(e2.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Se lasciata in esecuzione, salva con una frequenza impostata dall'utente le informazioni attuali su
	 * pressione e visibilità
	 *
	 * @param id         rappresenta la città di cui si richiedono le previsioni.
	 *                   Valore di default: 3169070 (Rome,IT)
	 * @param multiplier moltiplicatore dell'ora (Esempio: 0,5 salva i dati ogni 30
	 *                   minuti, 2 ogni 2 ore)
	 * @return Un messaggio di riepilogo con la frequenza e la prima misurazione
	 * @throws WrongMultiplyException eccezione lanciata se il moltiplicatore non è ammesso (moltiplicatore minore o uguale a 0.02).Un moltiplicatore di 0.02 restituirebbe dati ogni minuto circa
	 * @throws IdNotFoundException eccezione lanciata se non è stato trovato nessun ID corrispondente alla richiesta
	 * 
	 */
	@GetMapping(value = "/hourlySave")
	public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "id", defaultValue = "3169070") String id,
			@RequestParam(name = "multiplier", defaultValue = "1") double multiplier) throws WrongMultiplyException, IdNotFoundException {
		//String msg;
		try {

			service.saveToFileHourly(id, multiplier);
			/*
			if (multiplier != 1)
				msg = ((int) Math.round(multiplier*60)) + " minuti circa";
			else
				msg = "ora";
*/
			return new ResponseEntity<>(service.saveMessage(multiplier)
					+ service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
		} catch (IdNotFoundException |ItalianCityNotFoundException | WrongMultiplyException e2) {
			return new ResponseEntity<>(e2.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * Restituisce e salva le statistiche giornaliere di una città.
	 * 
	 * @param city rappresenta la città di cui si richiedono le statistiche. Valore di
	 *           default: Rome,IT
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @return Le statistiche per un giorno
	 */	
	@GetMapping(value="/oneDay")
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Rome") String city,

	@RequestParam(name = "date") String date)
	{
		try {
			statistics = new Statistics(city, date);
			//TODO aggiunto salvataggio (Funzionante)
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException | DayNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	//TODO Data nel json è sempre null (da risolvere)
	
	/**
	 * Restituisce e salva le statistiche per più giorni di una
	 * città.
	 * 
	 * @param city rappresenta la città di cui si richiedono le statistiche.Valore di
	 *           default: Rome,IT
	 * @param days Il numero di giorni di cui si vogliono ricevere statistiche
	 * @return Le statistiche per più giorni
	 */
	@GetMapping(value="/moreDays")
	public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Rome") String city,
												@RequestParam(name = "days") int days)
	{		
		try {
			statistics = new Statistics(city, days);
			//TODO aggiunto salvataggio (Funzionante)
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	//TODO Scrivere su json da che ora a che ora
	
	/**
	 * Restituisce e salva le statistiche per più ore di un giorno di una città. 
	 * 
	 * @param city rappresenta la città di cui si richiedono le statistiche. Valore
	 *             di default: Rome,IT
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @param from prima ora
	 * @param to   ultima ora
	 * @return Le statistiche per più ore  
	 * 
	 */
	@GetMapping(value = "/hourly")
	public ResponseEntity<Object> getStatisticsHourly(@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "date") String date, @RequestParam(name = "from") int from,
			@RequestParam(name = "to") int to) {
		try {
			statistics = new Statistics(city, date, from, to);
			// TODO aggiunto salvataggio (Funzionante)
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (WrongHoursPeriodException | CityStatisticsNotFoundException | DayNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

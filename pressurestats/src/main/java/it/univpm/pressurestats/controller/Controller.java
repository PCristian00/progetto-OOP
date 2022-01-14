package it.univpm.pressurestats.controller;

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
import it.univpm.pressurestats.exception.NegativeStartException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.exception.WrongMultiplyException;
import it.univpm.pressurestats.service.Service;
import it.univpm.pressurestats.statistics.Statistics;

/**
 * Processa le varie richieste, prepara il modello e restituisce una risposta.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
@RestController

public class Controller {

	/**
	 * Costruttore della classe.
	 */
	public Controller() {

	}

	/**
	 * Interfaccia Service usata per le operazioni di raccolta e salvataggio dati.
	 */
	@Autowired
	Service service;
	/**
	 * Oggetto Statitstics usato per le operazioni di generazione e salvataggio
	 * statistiche.
	 */
	Statistics statistics;

	/**
	 * Mostra le informazioni attuali su pressione e visibilità. I dati vengono
	 * stampati su schermo e salvati in un file di testo.
	 * 
	 * @param id rappresenta la città di cui si richiedono le previsioni. Valore di
	 *           default: 3169070 (Rome,IT)
	 * @return le previsioni meteo su pressione e visibilità della città richiesta e
	 *         le informazioni generali sulla città.
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 * @throws IdNotFoundException          eccezione lanciata se non è stato
	 *                                      trovato nessun ID corrispondente alla
	 *                                      richiesta
	 */
	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3169070") String id)
			throws ItalianCityNotFoundException, IdNotFoundException {

		try {
			service.saveToFile((service.getJSONForecast(id, true)));
			return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
		} catch (IdNotFoundException | ItalianCityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Se lasciata in esecuzione, salva con una frequenza impostata dall'utente le
	 * informazioni attuali su pressione e visibilita' di 5 città non scelte
	 * dall'utente.
	 *
	 * @param multiplier moltiplicatore dell'ora (Esempio: 0,5 salva i dati ogni 30
	 *                   minuti, 2 ogni 2 ore)
	 * @return Un messaggio di riepilogo con la frequenza
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 * @throws WrongMultiplyException       eccezione lanciata se il moltiplicatore
	 *                                      non è ammesso (moltiplicatore minore o
	 *                                      uguale a 0.02).Un moltiplicatore di 0.02
	 *                                      restituirebbe dati ogni minuto circa
	 * @throws NegativeStartException       eccezione lanciata se il valore di Start
	 *                                      è inferiore a 0. (Negativo)
	 * @throws IdNotFoundException          eccezione lanciata se non è stato
	 *                                      trovato nessun ID corrispondente alla
	 *                                      richiesta
	 */
	@GetMapping(value = "/multiSave")
	public ResponseEntity<Object> saveToFileHourly(
			@RequestParam(name = "multiplier", defaultValue = "1") double multiplier)
			throws ItalianCityNotFoundException, WrongMultiplyException, NegativeStartException, IdNotFoundException {
		try {
			// TODO Attenzione: Tenere le variabili Start separate tra loro di almeno 1000
			// (un secondo).
			// ROMA
			service.saveToFileHourly("3169070", multiplier, 1000);
			// NAPOLI
			service.saveToFileHourly("3172395", multiplier, 2000);
			// MILANO
			service.saveToFileHourly("6542283", multiplier, 3000);
			// ANCONA
			service.saveToFileHourly("6542126", multiplier, 4000);
			// PALERMO
			service.saveToFileHourly("2523920", multiplier, 5000);

			return new ResponseEntity<>(service.saveMessage(multiplier) + "Lasciare in esecuzione applicazione.",
					HttpStatus.OK);
		} catch (IdNotFoundException | ItalianCityNotFoundException | WrongMultiplyException
				| NegativeStartException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Se lasciata in esecuzione, salva con una frequenza impostata dall'utente le
	 * informazioni attuali su pressione e visibilità
	 *
	 * @param id         rappresenta la città di cui si richiedono le previsioni.
	 *                   Valore di default: 3169070 (Rome,IT)
	 * @param multiplier moltiplicatore dell'ora (Esempio: 0,5 salva i dati ogni 30
	 *                   minuti, 2 ogni 2 ore)
	 * @return Un messaggio di riepilogo con la frequenza e la prima misurazione
	 * @throws WrongMultiplyException eccezione lanciata se il moltiplicatore non è
	 *                                ammesso (moltiplicatore minore o uguale a
	 *                                0.02). Un moltiplicatore di 0.02 restituirebbe
	 *                                dati ogni minuto circa
	 * @throws IdNotFoundException    eccezione lanciata se non è stato trovato
	 *                                nessun ID corrispondente alla richiesta
	 * @throws NegativeStartException eccezione lanciata se il valore di Start è
	 *                                inferiore a 0. (Negativo)
	 * 
	 */
	@GetMapping(value = "/hourlySave")
	public ResponseEntity<Object> saveToFileHourly(@RequestParam(name = "id", defaultValue = "3169070") String id,
			@RequestParam(name = "multiplier", defaultValue = "1") double multiplier)
			throws WrongMultiplyException, IdNotFoundException, NegativeStartException {

		try {

			service.saveToFileHourly(id, multiplier, 0);

			return new ResponseEntity<>(
					service.saveMessage(multiplier) + service.getForecast(service.getJSONForecast(id, true)),
					HttpStatus.OK);
		} catch (IdNotFoundException | ItalianCityNotFoundException | WrongMultiplyException
				| NegativeStartException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Restituisce e salva le statistiche giornaliere di una città.
	 * 
	 * @param city rappresenta la città di cui si richiedono le statistiche. Valore
	 *             di default: Rome,IT
	 * @param date Il giorno di cui si vogliono ricevere statistiche
	 * @return Le statistiche per un giorno
	 */
	@GetMapping(value = "/oneDay")
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Rome") String city,

			@RequestParam(name = "date") String date) {
		try {
			statistics = new Statistics(city, date);
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException | DayNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Restituisce le statistiche di pressione e visibilità di una città data,
	 * filtrate dal giorno attuale a tot giorni passati.
	 * 
	 * @param city rappresenta la città di cui si richiedono le statistiche.Valore
	 *             di default: Rome,IT
	 * @param days Il numero di giorni di cui si vogliono ricevere statistiche
	 * @return Le statistiche per più giorni
	 */
	@GetMapping(value = "/moreDays")
	public ResponseEntity<Object> getStatisticsMoreDays(@RequestParam(name = "city", defaultValue = "Rome") String city,
			@RequestParam(name = "days") int days) {
		try {
			statistics = new Statistics(city, days);
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (CityStatisticsNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

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
			statistics.saveToFile(statistics.stats());
			return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
		} catch (WrongHoursPeriodException | CityStatisticsNotFoundException | DayNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}

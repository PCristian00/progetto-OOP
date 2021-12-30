package it.univpm.pressurestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.pressurestats.service.Service;


@RestController
public class Controller {
	@Autowired
	Service service;

	@GetMapping(value = "/current")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3173006") String id) {
		// TODO Portata all'esterno il metodo saveToFile per evitare ripetizioni in
		// saveToFileHourly (vedere getJSONForecast)
		service.saveToFile((service.getJSONForecast(id, true)));
		return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
	}

	// TODO nuova rotta per salvare un nuovo dato ogni ora
	@GetMapping(value = "/hourly")
	public String saveToFileHourly(@RequestParam(name = "id", defaultValue = "3173006") String id) {
		service.saveToFileHourly(id);

		// TODO trovare messaggio migliore o fare return di ResponseEntity (vedi
		// /current)
		return "Il salvataggio avverrà ogni ora, lasciare programma in esecuzione.";

	}
}

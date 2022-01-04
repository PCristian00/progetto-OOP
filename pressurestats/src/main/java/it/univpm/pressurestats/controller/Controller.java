package it.univpm.pressurestats.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.pressurestats.service.Service;
import it.univpm.pressurestats.statistics.Statistics;

//Perché restController??
@RestController
public class Controller {
	@Autowired
	Service service;
	Statistics statistics;
	
	@GetMapping(value="/prova")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3173006") String id)
	{
		return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
	}
	
	@GetMapping(value="/oneDay")
	public ResponseEntity<Object> getStatisticsOneDay(@RequestParam(name = "city", defaultValue = "Montecassiano") String city,
												@RequestParam(name = "date") String date)
	{
		statistics = new Statistics(city, date);
		return new ResponseEntity<>(statistics.stats(), HttpStatus.OK);
	}
	
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

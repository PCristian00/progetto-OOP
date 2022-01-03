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

//Perché restController??
@RestController
public class Controller {
	@Autowired
	Service service;
	OneDayStatistics statistics;
	MoreDaysStatistics more = new MoreDaysStatistics();
	
	@GetMapping(value="/prova")
	public ResponseEntity<Object> getForecast(@RequestParam(name = "id", defaultValue = "3173006") String id)
	{
		return new ResponseEntity<>(service.getForecast(service.getJSONForecast(id, true)), HttpStatus.OK);
	}
	
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

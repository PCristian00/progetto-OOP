package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.model.City;
import it.univpm.pressurestats.service.ServiceImpl;

class TestService {

	ServiceImpl s;
	City city;
	
	@BeforeEach
	void setUp() throws Exception {
		s = new ServiceImpl();
		city = s.getForecast(s.getJSONForecast("3169070", true));
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testService() throws ItalianCityNotFoundException {
		assertNotNull(s.getJSONForecast("3169070", true));
		assertNotNull(s.getForecast(s.getJSONForecast("3169070", true)));
		assertNotNull(s.toJSON(city));
	}

}

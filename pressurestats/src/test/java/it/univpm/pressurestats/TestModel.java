package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.model.City;
import it.univpm.pressurestats.model.Forecast;
import it.univpm.pressurestats.service.ServiceImpl;

class TestModel {
	
	private Vector<Forecast> f = null;
	private City c = null;
	private ServiceImpl s = null;
 
	@BeforeEach
	void setUp() throws Exception {
		s = new ServiceImpl();
		c = s.getForecast(s.getJSONForecast("3169070", true));
		f = c.getWeather();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testCity() {
		assertEquals("Rome", c.getName());
		assertEquals("IT", c.getCountry());
		assertEquals(12.4839, c.getLon());
		assertEquals(41.8947, c.getLat());
		assertEquals(3169070, c.getId());
		assertNotNull(c.getWeather());
	}
	
	@Test
	void testForecast() {
		for (Forecast forecast : f) {
			assertNotNull(forecast.getDate());
			assertNotNull(forecast.getDt());
			assertNotNull(forecast.getPressure());
			assertNotNull(forecast.getVisibility());
		}
	}

}

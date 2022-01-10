package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.model.City;
import it.univpm.pressurestats.model.Forecast;
import it.univpm.pressurestats.service.ServiceImpl;
/**
 * Classe che testa i vari modelli.
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
class TestModel {
	/**
	 * Vettore di previsioni usato per test.
	 */
	private Vector<Forecast> f = null;
	/**
	 * Città usato per test.
	 */
	private City c = null;
	/**
	 * Implementazione di Service usata per test.
	 */
	private ServiceImpl s = null;
	/**
	 * Imposta le variabili per i test.
	 * @throws Exception Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception {
		s = new ServiceImpl();
		c = s.getForecast(s.getJSONForecast("3169070", true));
		f = c.getWeather();
	}
	/**
	 * Usato per rilasciare le risorse dopo che i test sono eseguiti.
	 * @throws Exception Eccezione
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Testa funzioni della classe City.
	 */
	@Test
	void testCity() {
		assertEquals("Rome", c.getName());
		assertEquals("IT", c.getCountry());
		assertEquals(12.4839, c.getLon());
		assertEquals(41.8947, c.getLat());
		assertEquals(3169070, c.getId());
		assertNotNull(c.getWeather());
	}
	/**
	 * Testa funzioni della classe Forecast.
	 */
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

package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.exception.IdNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.model.City;
import it.univpm.pressurestats.service.ServiceImpl;

/**
 * Testa Service.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
class TestService {
	/**
	 * Implementazione di Service usata per test
	 */
	ServiceImpl s;
	/**
	 * Città usata per test
	 */
	City city;

	/**
	 * Costruttore della classe.
	 */
	public TestService() {

	}

	/**
	 * Imposta le variabili per i test.
	 * 
	 * @throws Exception Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception {
		s = new ServiceImpl();
		city = s.getForecast(s.getJSONForecast("3169070", true));
	}

	/**
	 * Usato per rilasciare le risorse dopo che i test sono eseguiti.
	 * 
	 * @throws Exception Eccezione
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Testa i metodi di Service
	 * 
	 * @throws ItalianCityNotFoundException eccezione lanciata se la città non è
	 *                                      italiana
	 * @throws IdNotFoundException          eccezione lanciata se non è stato
	 *                                      trovato nessun ID corrispondente alla
	 *                                      richiesta
	 */
	@Test
	void testService() throws ItalianCityNotFoundException, IdNotFoundException {
		assertNotNull(s.getJSONForecast("3169070", true));
		assertNotNull(s.getForecast(s.getJSONForecast("3169070", true)));
		assertNotNull(s.toJSON(city));
	}

}

package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.exception.WrongMultiplyException;
import it.univpm.pressurestats.service.ServiceImpl;
import it.univpm.pressurestats.statistics.Filters;
/**
 * Classe che testa le eccezioni.
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
class TestThrows {
/**
 * Implementazione di Service usata per test
 */
	private ServiceImpl c = null;
	/**
	 * Filtri usati per test
	 */
	private Filters f = null;
	/**
	 * Imposta le variabili per i test.
	 * @throws Exception Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception {
		c = new ServiceImpl();
		f = new Filters();
	}
/**
 * Usato per rilasciare le risorse dopo che i test sono eseguiti.
 * @throws Exception Eccezione
 */
	@AfterEach
	void tearDown() throws Exception {
	}
/**
 * Testa le eccezioni personalizzate di PressureStats.
 */
	@Test
	void testException() {
		assertThrows(ItalianCityNotFoundException.class, ()->c.saveToFile(c.getJSONForecast("2172797", true)));
		assertThrows(CityStatisticsNotFoundException.class, ()->f.moreDayWeather("Ascoli", 3));
		assertThrows(DayNotFoundException.class, ()->f.oneDayWeather("Rome", "15-10-2022"));
		assertThrows(WrongHoursPeriodException.class, ()->f.hourly("Rome", "07-01-2022", 10, 0));
		assertThrows(WrongMultiplyException.class, ()->c.saveToFileHourly("3169070", 0.01));
	}
}

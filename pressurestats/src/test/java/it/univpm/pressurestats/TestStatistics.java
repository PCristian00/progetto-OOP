package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.statistics.Filters;

/**
 * Testa le statistiche.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
class TestStatistics {
	/**
	 * Filtri usati per i test
	 */
	private Filters f;

	/**
	 * Costruttore della classe.
	 */
	public TestStatistics() {

	}

	/**
	 * Imposta le variabili per i test.
	 * 
	 * @throws Exception Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception {
		f = new Filters();
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
	 * Testa metodi di Filters
	 * 
	 * @throws WrongHoursPeriodException       eccezione lanciata se il range orario
	 *                                         non è corretto
	 * @throws CityStatisticsNotFoundException eccezione lanciata se le statistiche
	 *                                         della città non sono state trovate
	 * @throws DayNotFoundException            eccezione lanciata se il giorno non è
	 *                                         stato trovato
	 */
	@Test
	void testFilters() throws WrongHoursPeriodException, CityStatisticsNotFoundException, DayNotFoundException {
		assertNotNull(f.hourly("Rome", "07-01-2022", 0, 7));
		assertNotNull(f.moreDayWeather("Milan", 3));
		assertNotNull(f.oneDayWeather("Ancona", "07-01-2022"));
	}
}

package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.exception.CityStatisticsNotFoundException;
import it.univpm.pressurestats.exception.DayNotFoundException;
import it.univpm.pressurestats.exception.WrongHoursPeriodException;
import it.univpm.pressurestats.statistics.Filters;

class TestStatistics {
	
	private Filters f;

	@BeforeEach
	void setUp() throws Exception {
		f = new Filters();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testFilters() throws WrongHoursPeriodException, CityStatisticsNotFoundException, DayNotFoundException {
		assertNotNull(f.hourly("Rome", "07-01-2022", 0, 7));
		assertNotNull(f.moreDayWeather("Milan", 3));
		assertNotNull(f.oneDayWeather("Ancona", "07-01-2022"));
	}
}

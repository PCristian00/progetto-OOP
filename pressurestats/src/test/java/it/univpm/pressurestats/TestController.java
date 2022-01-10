package it.univpm.pressurestats;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.univpm.pressurestats.controller.Controller;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.WrongMultiplyException;

class TestController {
	
	private Controller c = new Controller();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testController() throws ItalianCityNotFoundException, WrongMultiplyException {
		assertNotNull(c.getForecast("3169070"));
		assertNotNull(c.getStatisticsHourly("Rome", "07-01-2022", 0, 7));
		assertNotNull(c.getStatisticsMoreDays("Palermo", 3));
		assertNotNull(c.getStatisticsOneDay("Milan", "08-01-2022"));
		assertNotNull(c.saveToFileHourly("3169070", 0.5));
		assertNotNull(c.saveToFileHourly(1.0));
	}
}

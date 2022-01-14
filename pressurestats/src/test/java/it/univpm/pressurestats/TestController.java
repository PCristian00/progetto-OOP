package it.univpm.pressurestats;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
/**
 * Testa il Controller.
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
class TestController {
	/**
	 * Costruttore della classe;
	 */
	public TestController() {
		
	}
	/**
	 * Rende disponibili al test le applicazioni web senza usare una vera comunicazione HTTP.
	 */
	@Autowired
	private MockMvc mockMvc;
	/**
	 * Imposta le variabili per i test.
	 * @throws Exception Eccezione
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}
	/**
	 * Usato per rilasciare le risorse dopo che i test sono eseguiti.
	 * @throws Exception Eccezione
	 */
	@AfterEach
	void tearDown() throws Exception {
	}
	/**
	 * Testa rotte del Controller.
	 * @throws Exception Eccezione
	 */
	@Test
	void testController() throws Exception {
		this.mockMvc.perform(get("/current")).andExpect(status().isOk());
		this.mockMvc.perform(get("/multiSave")).andExpect(status().isOk());
		this.mockMvc.perform(get("/hourlySave")).andExpect(status().isOk());
		this.mockMvc.perform(get("/oneDay?date=07-01-2022")).andExpect(status().isOk());
		this.mockMvc.perform(get("/moreDays?days=3")).andExpect(status().isOk());
		this.mockMvc.perform(get("/hourly?date=07-01-2022&city=Palermo&from=0&to=7")).andExpect(status().isOk());
	}
}

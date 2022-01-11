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

@SpringBootTest
@AutoConfigureMockMvc
class TestController {
	
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		
	}

	@AfterEach
	void tearDown() throws Exception {
	}

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

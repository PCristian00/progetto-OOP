package it.univpm.pressurestats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Avvia l'applicazione Spring.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *
 */
@SpringBootApplication
public class PressurestatsApplication {
	//TODO manca il costruttore: Non serve, ma valutare di scriverlo per completare JavaDoc
	/**
	 * Avvia main che avvia l'applicazione SpringBoot
	 * 
	 * @param args linee di comando JAVA (MODIFICARE)
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(PressurestatsApplication.class, args);
	}
}

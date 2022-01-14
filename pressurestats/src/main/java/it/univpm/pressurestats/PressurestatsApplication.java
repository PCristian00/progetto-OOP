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
	/**
	 * Costruttore della classe.
	 */

	public PressurestatsApplication() {

	}

	/**
	 * Avvia main che avvia l'applicazione SpringBoot
	 * 
	 * @param args argomenti linee di comando JAVA
	 * 
	 */
	public static void main(String[] args) {
		SpringApplication.run(PressurestatsApplication.class, args);
	}

}

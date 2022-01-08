package it.univpm.pressurestats.exception;

/**
 * Eccezione lanciata se il giorno non è stato trovato.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
@SuppressWarnings("serial")
public class DayNotFoundException extends Exception {
	/**
	 * Messaggio di errore
	 * 
	 */
	private String message;

	/**
	 * Costruttore della classe.
	 * 
	 * @param message messaggio di errore
	 */
	public DayNotFoundException(String message) {
		this.message = message;
	}

	/**
	 * Restituisce la stringa di errore dell'eccezione.
	 * 
	 * @return il messaggio di errore
	 */
	public String getMessage() {
		return message;
	}
}

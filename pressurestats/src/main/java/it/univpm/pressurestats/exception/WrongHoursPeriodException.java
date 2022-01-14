package it.univpm.pressurestats.exception;

/**
 * Eccezione lanciata se il range orario non è corretto.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class WrongHoursPeriodException extends Exception {
	/**
	 * Seriale eccezione
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Messaggio di errore.
	 */
	private String message;

	/**
	 * Costruttore della classe.
	 * 
	 * @param message messaggio di errore
	 */
	public WrongHoursPeriodException(String message) {
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

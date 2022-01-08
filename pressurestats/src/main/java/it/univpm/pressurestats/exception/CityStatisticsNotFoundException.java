package it.univpm.pressurestats.exception;

/**
 * Eccezione lanciata se le statistiche della città non sono state trovate.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
//@SuppressWarnings("serial")
public class CityStatisticsNotFoundException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
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
	public CityStatisticsNotFoundException(String message) {
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

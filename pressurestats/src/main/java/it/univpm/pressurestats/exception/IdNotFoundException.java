package it.univpm.pressurestats.exception;

/**
 * Eccezione lanciata se non è stato trovato nessun ID corrispondente alla richiesta.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class IdNotFoundException extends Exception {
	/**
	 * Seriale eccezione
	 */
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
	public IdNotFoundException(String message) {
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

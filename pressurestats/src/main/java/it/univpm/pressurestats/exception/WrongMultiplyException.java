package it.univpm.pressurestats.exception;
/**
 * Eccezione lanciata se il moltiplicatore non è ammesso (moltiplicatore minore o uguale a zero).
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class WrongMultiplyException extends Exception {
private static final long serialVersionUID = 1L;
	
	/**
	 * Messaggio di errore
	 * 
	 */
	String message;

	/**
	 * Costruttore della classe.
	 * 
	 * @param message messaggio di errore
	 */
	public WrongMultiplyException(String message) {
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

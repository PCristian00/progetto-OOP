package it.univpm.pressurestats.exception;
/**
 * Eccezione lanciata se il valore di Start è inferiore a 0. (Negativo)
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class NegativeStartException extends Exception {
/**
 * Seriale eccezione
 */
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
	public NegativeStartException(String message) {
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

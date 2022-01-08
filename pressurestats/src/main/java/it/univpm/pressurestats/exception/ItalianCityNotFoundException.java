package it.univpm.pressurestats.exception;
/**
 * Eccezione lanciata se la città non è italiana.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 *  
 */
@SuppressWarnings("serial")
public class ItalianCityNotFoundException extends Exception{
	/**
	 * Messaggio di errore
	 * 
	 */
	String message;
	/**
	 * Costruttore della classe.
	 * @param message messaggio di errore
	 */
	public ItalianCityNotFoundException(String message)
	{
		this.message = message;
	}
	/**
	 * Restituisce la stringa di errore dell'eccezione.
	 * @return il messaggio di errore
	 */
	public String getMessage()
	{
		return message;
	}
}

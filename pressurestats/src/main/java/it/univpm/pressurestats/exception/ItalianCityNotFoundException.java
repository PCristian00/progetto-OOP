package it.univpm.pressurestats.exception;

public class ItalianCityNotFoundException extends Exception{

	String message;
	
	public ItalianCityNotFoundException(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}

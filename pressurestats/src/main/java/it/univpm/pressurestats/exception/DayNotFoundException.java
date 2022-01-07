package it.univpm.pressurestats.exception;

public class DayNotFoundException extends Exception{
	
	private String message;
	
	public DayNotFoundException(String message)
	{
		this.message = message;
	}

	public String getMessage() 
	{
		return message;
	}
}

package it.univpm.pressurestats.exception;

public class WrongHoursPeriodException extends Exception{

	private String message;
	
	public WrongHoursPeriodException(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}

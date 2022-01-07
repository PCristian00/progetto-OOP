package it.univpm.pressurestats.exception;

public class CityStatisticsNotFoundException extends Exception{

	private String message;
	
	public CityStatisticsNotFoundException(String message)
	{
		this.message = message;
	}
	
	public String getMessage()
	{
		return message;
	}
}

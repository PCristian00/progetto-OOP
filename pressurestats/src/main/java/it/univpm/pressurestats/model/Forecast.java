package it.univpm.pressurestats.model;
//Statistiche meteo pressione
public class Forecast {
	private int pressure;
	private int visibility;
	private long dt;
	private String date;
	
	public Forecast() {
		super();
		this.pressure = 0;
		this.visibility = 0;
		this.dt = 0;
		this.date = null;
	}

	public int getPressure() {
		return pressure;
	}

	public void setPressure(int pressure) {
		this.pressure = pressure;
	}

	public int getVisibility() {
		return visibility;
	}

	public void setVisibility(int visibility) {
		this.visibility = visibility;
	}

	public long getDt() {
		return dt;
	}

	public void setDt(long dt) {
		this.dt = dt;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	

}

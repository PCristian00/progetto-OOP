package it.univpm.pressurestats.model;

import java.util.Objects;

//Statistiche meteo pressione
public class Forecast {
	private long pressure;
	private long visibility;
	private long dt;
	private String date;
	
	public Forecast() {
		super();
		this.pressure = 0;
		this.visibility = 0;
		this.dt = 0;
		this.date = null;
	}

	public long getPressure() {
		return pressure;
	}

	public void setPressure(long pressure) {
		this.pressure = pressure;
	}

	public long getVisibility() {
		return visibility;
	}

	public void setVisibility(long visibility) {
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

	@Override
	public String toString() {
		return "pressure=" + pressure + ", visibility=" + visibility + ", dt=" + dt + ", date=" + date;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Forecast other = (Forecast) obj;
		return Objects.equals(date, other.date) && dt == other.dt && pressure == other.pressure
				&& visibility == other.visibility;
	}
	
	
}

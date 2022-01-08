package it.univpm.pressurestats.model;

import java.util.Objects;

/**
 * La classe Forecast contiene i dati di pressione, visibilità e la data della
 * loro misurazione.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class Forecast {
	private long pressure;
	private long visibility;
	private long dt;
	private String date;

	/**
	 * Costruttore dell'oggetto.
	 * 
	 */
	public Forecast() {
		super();
		this.pressure = 0;
		this.visibility = 0;
		this.dt = 0;
		this.date = null;
	}

	/**
	 * Restituisce pressione
	 * 
	 * @return pressure
	 */
	public long getPressure() {
		return pressure;
	}

	/**
	 * Imposta nuovo valore di pressione
	 * 
	 * @param pressure nuovo valore di pressione
	 */
	public void setPressure(long pressure) {
		this.pressure = pressure;
	}

	/**
	 * Restituisce visibilità
	 * 
	 * @return visibility
	 */
	public long getVisibility() {
		return visibility;
	}

	/**
	 * Imposta nuovo valore di visibilità
	 * 
	 * @param visibility nuovo valore di visibilità
	 */
	public void setVisibility(long visibility) {
		this.visibility = visibility;
	}

	/**
	 * Restituisce data misurazione in formato Unix epoch (long) (Secondi passanti
	 * dal 1 gennaio 1970)
	 * 
	 * @return dt
	 */
	public long getDt() {
		return dt;
	}

	/**
	 * Imposta nuova data misurazione in formato Unix epoch (long) (Secondi passanti
	 * dal 1 gennaio 1970)
	 * 
	 * @param dt nuova data
	 */
	public void setDt(long dt) {
		this.dt = dt;
	}

	/**
	 * Restituisce data misurazione in formato standard
	 * 
	 * @return date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Imposta nuova data misurazione in formato standard
	 * 
	 * @param date nuova data
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Restituisce le previsioni sotto forma di Stringa
	 * 
	 * @return String forecast
	 */
	@Override
	public String toString() {
		return "pressure=" + pressure + ", visibility=" + visibility + ", dt=" + dt + ", date=" + date;
	}

	/**
	 * Confronta una previsione con un'altra
	 * 
	 * @param obj la previsione con cui confrontare
	 * @return true || false
	 */
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

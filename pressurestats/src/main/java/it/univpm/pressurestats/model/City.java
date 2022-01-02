package it.univpm.pressurestats.model;
//Classe città

import java.util.Objects;
import java.util.Vector;

/**
 * La classe City contiene le proprieta' della citta' e le relative previsioni.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 * 
 */
public class City {
	private long id;
	private String name;
	private String country;
	private double lat;
	private double lon;
	private Vector<Forecast> weather;

	/**
	 * Costruttore dell'oggetto.
	 * 
	 */
	public City() {
		this.id = 0;
		this.name = null;
		this.country = null;
		this.lat = 0;
		this.lon = 0;
		this.weather = null;
	}

	/**
	 * Costruttore dell'oggetto con nome come argomento.
	 * 
	 * @param name nome della citta'
	 */

	public City(String name) {
		this.id = 0;
		this.name = name;
		this.country = null;
		this.lat = 0;
		this.lon = 0;
		this.weather = null;
	}

	/**
	 * Restituisce id della citta'
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Imposta nuovo id della citta'
	 * 
	 * @param id nuovo id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Restituisce nome della citta'
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Imposta nuovo id della citta'
	 * 
	 * @param name nuovo nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce nazione della citta'
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Imposta nuova nazione della citta'
	 * 
	 * @param country nuova nazione
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Restituisce latitudine della citta'
	 * 
	 * @return lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Imposta nuova latitudine della citta'
	 * 
	 * @param lat nuova latitudine
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Restituisce longitudine della citta'
	 * 
	 * @return lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * Imposta nuova longitudine della citta'
	 * 
	 * @param lon nuova longitudine
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * Restituisce previsioni meteo della citta'
	 * 
	 * @return weather
	 */
	public Vector<Forecast> getWeather() {
		return weather;
	}

	/**
	 * Imposta nuove previsioni meteo della citta'
	 * 
	 * @param weather nuove previsioni meteo
	 */
	public void setWeather(Vector<Forecast> weather) {
		this.weather = weather;
	}

	/**
	 * Restituisce la citta' sotto forma di Stringa
	 * 
	 * @return String city
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", country=" + country + ", lat=" + lat + ", lon=" + lon + ", weather="
				+ weather;
	}

	/**
	 * Confronta una citta' con un'altra
	 * 
	 * @param obj la citta' con cui confrontare
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
		City other = (City) obj;
		return Objects.equals(country, other.country) && id == other.id && lat == other.lat && lon == other.lon
				&& Objects.equals(name, other.name) && Objects.equals(weather, other.weather);
	}

}

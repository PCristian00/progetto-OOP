package it.univpm.pressurestats.model;

import java.util.Objects;
import java.util.Vector;

/**
 * La classe City contiene le proprietà della città e le relative previsioni.
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
	 * Costruttore dell'oggetto.	 * 
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
	 * Costruttore dell'oggetto con nome come parametro.
	 * 
	 * @param name nome della città
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
	 * Restituisce id della città
	 * 
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Imposta nuovo id della città
	 * 
	 * @param id nuovo id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Restituisce nome della città
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Imposta nuovo nome della città
	 * 
	 * @param name nuovo nome
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Restituisce nazione della città
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Imposta nuova nazione della città
	 * 
	 * @param country nuova nazione
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Restituisce latitudine della città
	 * 
	 * @return lat
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * Imposta nuova latitudine della città
	 * 
	 * @param lat nuova latitudine
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}

	/**
	 * Restituisce longitudine della città
	 * 
	 * @return lon
	 */
	public double getLon() {
		return lon;
	}

	/**
	 * Imposta nuova longitudine della città
	 * 
	 * @param lon nuova longitudine
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}

	/**
	 * Restituisce previsioni meteo della città
	 * 
	 * @return weather
	 */
	public Vector<Forecast> getWeather() {
		return weather;
	}

	/**
	 * Imposta nuove previsioni meteo della città
	 * 
	 * @param weather nuove previsioni meteo
	 */
	public void setWeather(Vector<Forecast> weather) {
		this.weather = weather;
	}

	/**
	 * Restituisce la città sotto forma di Stringa
	 * 
	 * @return String city
	 */
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", country=" + country + ", lat=" + lat + ", lon=" + lon + ", weather="
				+ weather;
	}

	/**
	 * Confronta una città con un'altra
	 * 
	 * @param obj la città con cui confrontare
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

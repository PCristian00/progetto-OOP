package it.univpm.pressurestats.model;
//Classe città

import java.util.Vector;

public class City {
	private long id;
	private String name;
	private String country;
	private long lat;
	private long lon;
	private Vector<Forecast> weather;
	
	public City()
	{
		this.id = 0;
		this.name = null;
		this.country = null;
		this.lat = 0;
		this.lon = 0;
		this.weather = null;
	}

	public City(String name) {
		this.id = 0;
		this.name = name;
		this.country = null;
		this.lat = 0;
		this.lon = 0;
		this.weather = null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public long getLat() {
		return lat;
	}

	public void setLat(long lat) {
		this.lat = lat;
	}

	public long getLon() {
		return lon;
	}

	public void setLon(long lon) {
		this.lon = lon;
	}

	public Vector<Forecast> getWeather() {
		return weather;
	}

	public void setWeather(Vector<Forecast> weather) {
		this.weather = weather;
	}

}

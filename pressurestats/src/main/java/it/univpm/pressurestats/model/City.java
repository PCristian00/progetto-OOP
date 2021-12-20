package it.univpm.pressurestats.model;
//Classe città

import java.util.Objects;
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

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", country=" + country + ", lat=" + lat + ", lon=" + lon
				+ ", weather=" + weather;
	}

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

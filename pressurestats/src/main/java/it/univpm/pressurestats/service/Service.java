package it.univpm.pressurestats.service;

import org.json.simple.JSONObject;
import it.univpm.pressurestats.model.City;

public interface Service {
public abstract JSONObject toJSON(City city);
public abstract JSONObject getJSONForecast(String name, boolean isObject);
public abstract City getForecast(JSONObject forecast);
public abstract void saveToFile(JSONObject obj);
}



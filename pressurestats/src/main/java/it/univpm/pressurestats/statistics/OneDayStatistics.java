package it.univpm.pressurestats.statistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

public class OneDayStatistics {

	@SuppressWarnings("unchecked")
	public JSONArray oneDayWeather(String city, String day, boolean isObject)
	{
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = System.getProperty("user.dir") + city + ".txt";
		
		try {
			BufferedReader buff =  new BufferedReader(new FileReader(nome_file));
			while((data = buff.readLine()) != null)
				ja.add((JSONObject) JSONValue.parseWithException(data));
			buff.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return ja;
	}
	
	public Vector<Long> oneDayVisibility()
	{
		return null;
	}
}

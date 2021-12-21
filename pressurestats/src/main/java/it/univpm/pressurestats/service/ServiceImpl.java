package it.univpm.pressurestats.service;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

import it.univpm.pressurestats.model.*;

public class ServiceImpl implements Service {

	private String apiKey = "07354f54b9a837406fecf6a8ccd2c217";
	private String url = "api.openweathermap.org/data/2.5/onecall?";
	JSONObject forecast = null;

	@Override

	public JSONObject toJSON(City city) {
		// TODO Auto-generated method stub

		try {
			long lat = city.getLat();
			long lon = city.getLon();

			url += "lat=" + lat + "&lon=" + lon + "&appid=" + apiKey + "&units=metric&exclude=minutely,hourly,alerts";

			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();

			String data = "";
			String line = "";
			try {
				InputStreamReader inR = new InputStreamReader(in);
				BufferedReader buf = new BufferedReader(inR);

				while ((line = buf.readLine()) != null) {
					data += line;
				}
			} finally {
				in.close();
			}
			try {
				forecast = (JSONObject) JSONValue.parseWithException(data);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	@Override
	public JSONObject getJSONForecast(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	
	public City getForecast(JSONObject obj) {
		City city=new City();
		Vector<Forecast> forecasts=new Vector<Forecast>();
		JSONObject cityData=(JSONObject) obj.get("city");
		JSONArray list=(JSONArray) obj.get("list");
		
		city.setName((String) cityData.get("country"));
		city.setId(String.valueOf(cityData.get("id")));
	
		for(int j=0; j<list.size(); j++) {
			JSONObject listElement=(JSONObject) list.get(j);
			Forecast singleForecast=new Forecast();
			
			singleForecast.setPressure((int)listElement.get("pressure"));
			singleForecast.setVisibilty((int)listElement.get("visibility"));
			singleForecast.setDt((long)listElement.get("dt"));
			forecasts.add(singleForecast);
		}
		
		city.setWeather(forecasts);
		return null;
	}

	@Override
	public void saveToFile(JSONObject object) {
		// TODO Auto-generated method stub
		City city = this.getForecast(object);
		String cityName = city.getName();
		JSONObject obj = new JSONObject();
		obj = toJSON(city);
		SimpleDateFormat date = new SimpleDateFormat("yyyy-mm-dd");
		String today = date.format(new Date());
		String fileName = cityName + "_" + today;
		String path = System.getProperty("user.dir") + fileName + ".txt";

		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter(new FileWriter(path)));
			file_output.println(obj.toString());
			file_output.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

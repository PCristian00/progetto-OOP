package it.univpm.pressurestats.service;

import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import java.io.*;

import org.json.simple.*;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import it.univpm.pressurestats.model.*;

//Perché @Service
@Service
public class ServiceImpl implements it.univpm.pressurestats.service.Service {

	private String apiKey = "07354f54b9a837406fecf6a8ccd2c217";
	JSONObject forecast = null;

	@Override

	public JSONObject toJSON(City city) {
		// TODO Auto-generated method stub
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(city);
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = mapper.readValue(json, Map.class);
			forecast = new JSONObject(map);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forecast;
	}

	@Override
	public JSONObject getJSONForecast(String id, boolean isObject) {
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		String url = "https://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=" + apiKey;
		try {
			URLConnection openConnection = new URL(url).openConnection();
			InputStream in = openConnection.getInputStream();
			
			String data = "";
			String line = "";
			try {
			   InputStreamReader inR = new InputStreamReader( in );
			   BufferedReader buf = new BufferedReader( inR );
			  
			   while ( ( line = buf.readLine() ) != null ) {
				   data+= line;
			   }
			} finally {
			   in.close();
			}
			if(isObject) {
				jo = (JSONObject) JSONValue.parseWithException(data); //parse JSON Object
			} else {
				ja = (JSONArray) JSONValue.parseWithException(data);	//parse JSON Array
				System.out.println("JSONArray scaricato: "+ ja);
				System.out.println("IL JSONArray scaricato ha "+ ja.size()+" elementi:");
				for(int i=0;i<ja.size();i++) {
					jo = (JSONObject)ja.get(i);
				}
			}
				
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		saveToFile(jo);
		return jo;
	}

	@Override
	
	public City getForecast(JSONObject obj) {
		City city=new City();
		Vector<Forecast> forecasts=new Vector<Forecast>();
		JSONObject cityData = (JSONObject) obj.get("main");
		JSONObject coord = (JSONObject) obj.get("coord");
		JSONObject sys = (JSONObject) obj.get("sys");
		Forecast currentForecast=new Forecast();
		
		currentForecast.setVisibility((long) obj.get("visibility"));
		currentForecast.setPressure((long) cityData.get("pressure"));
		
		city.setLat((double) coord.get("lat"));
		city.setLon((double) coord.get("lon"));
		city.setName((String) obj.get("name"));
		city.setId((long) obj.get("id"));
		city.setCountry((String) sys.get("country"));
		
		Date date = new Date( ((long)obj.get("dt")) * 1000 );
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		
		currentForecast.setDate(df.format(date));
		currentForecast.setDt((long)obj.get("dt"));
		
		forecasts.add(currentForecast);
		
		city.setWeather(forecasts);
		return city;
	}

	@Override
	public void saveToFile(JSONObject object) {
		// TODO Auto-generated method stub
		City city = this.getForecast(object);
		String cityName = city.getName();
		JSONObject obj = new JSONObject();
		obj = toJSON(city);
		String path = System.getProperty("user.dir") + cityName + ".txt";

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
			pw.append(obj.toJSONString()+"\n");
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

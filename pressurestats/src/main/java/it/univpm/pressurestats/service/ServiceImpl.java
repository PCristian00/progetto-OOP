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

import it.univpm.pressurestats.exception.IdNotFoundException;
import it.univpm.pressurestats.exception.ItalianCityNotFoundException;
import it.univpm.pressurestats.exception.NegativeStartException;
import it.univpm.pressurestats.exception.WrongMultiplyException;
import it.univpm.pressurestats.model.*;

//TODO Forse @Service è preferibile metterlo nell'interfaccia

/**
 * Implementazione dell'interfaccia Service. Contiene i
 * metodi utilizzati dal controller.
 * 
 * @author Pietroniro Cristian
 * @author Settimi Diego
 */
@Service
public class ServiceImpl implements it.univpm.pressurestats.service.Service {
	/**
	 * API Key
	 * 
	 */
	private String apiKey = "5a32bbb372f0b50deba8939136c59500";
	
	/**
	 * Rappresenta la previsione
	 * 
	 */
	JSONObject forecast = null;
	
	//TODO Impostazioni del salvataggio file, usate anche in Filters
	
	/**
	 * Percorso di salvataggio del file
	 * 
	 */
	public static String dir=System.getProperty("user.dir") + "/src/main/resources/";
	/**
	 * Suffisso nome file ed estensione
	 * 
	 */
	public static String f_type="_data.txt";
	/**
	 * Ora in millisecondi, costante
	 */
	static final long hour = 3600000;
	/**
	 * Costruttore della classe
	 */
	public ServiceImpl() {
	}
	@Override	
	public JSONObject toJSON(City city) {
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(city);
			ObjectMapper mapper = new ObjectMapper();
			@SuppressWarnings("unchecked")
			Map<String, Object> map = mapper.readValue(json, Map.class);
			forecast = new JSONObject(map);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return forecast;
	}

	@Override
	public JSONObject getJSONForecast(String id, boolean isObject) throws IdNotFoundException {
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject();
		String url = "https://api.openweathermap.org/data/2.5/weather?id=" + id + "&appid=" + apiKey;
		try {
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
			if (isObject) {
				jo = (JSONObject) JSONValue.parseWithException(data); // parse JSON Object
			} else {
				ja = (JSONArray) JSONValue.parseWithException(data); // parse JSON Array
				System.out.println("JSONArray scaricato: " + ja);
				System.out.println("IL JSONArray scaricato ha " + ja.size() + " elementi:");
				for (int i = 0; i < ja.size(); i++) {
					jo = (JSONObject) ja.get(i);
				}
			}

		} catch (IOException | ParseException e) {
			throw new IdNotFoundException("ID non trovato");

		} 

		return jo;
	}

	@Override

	public City getForecast(JSONObject obj) throws ItalianCityNotFoundException {

		City city = new City();
		Vector<Forecast> forecasts = new Vector<Forecast>();

		JSONObject cityData = (JSONObject) obj.get("main");
		JSONObject coord = (JSONObject) obj.get("coord");
		JSONObject sys = (JSONObject) obj.get("sys");
		Forecast currentForecast = new Forecast();

		currentForecast.setVisibility((long) obj.get("visibility"));
		currentForecast.setPressure((long) cityData.get("pressure"));

		city.setLat((double) coord.get("lat"));
		city.setLon((double) coord.get("lon"));
		city.setName((String) obj.get("name"));
		city.setId((long) obj.get("id"));
		if(!((String) sys.get("country")).equals("IT"))
			throw new ItalianCityNotFoundException("La città non è italiana");
		city.setCountry((String) sys.get("country"));

		Date date = new Date(((long) obj.get("dt")) * 1000);
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		currentForecast.setDate(df.format(date));
		currentForecast.setDt((long) obj.get("dt"));

		forecasts.add(currentForecast);

		city.setWeather(forecasts);
		return city;
	}

	@Override
	public void saveToFile(JSONObject object) throws ItalianCityNotFoundException {

		City city = this.getForecast(object);		
		String path=dir+city.getName()+f_type;
		JSONObject obj = toJSON(city);

		try {
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
			pw.append(obj.toJSONString() + "\n");
			pw.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveToFileHourly(String id, double multiplier, long start)
			throws ItalianCityNotFoundException, WrongMultiplyException, NegativeStartException, IdNotFoundException {
		if (multiplier <= 0.02)
			throw new WrongMultiplyException("Moltiplicatore non ammesso.");
		if (start < 0)
			throw new NegativeStartException("Il millisecondo di partenza non può essere inferiore a zero");

		TimerTask tt = new TimerTask() {
			public void run() {
				try {
					saveToFile(getJSONForecast(id, true));
				} catch (IdNotFoundException | ItalianCityNotFoundException e) {
					e.printStackTrace();
				}
			}
		};

		Timer timer = new Timer();
		timer.schedule(tt, start, (long) (multiplier * hour)); // ogni ora
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONArray readFile(String city) {
		JSONArray ja = new JSONArray();
		String data = "";
		String nome_file = dir + city + f_type;

		try {
			BufferedReader buff = new BufferedReader(new FileReader(nome_file));
			while ((data = buff.readLine()) != null)
				ja.add((JSONObject) JSONValue.parseWithException(data));
			buff.close();
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		}
		return ja;
	}


public String saveMessage(double multiplier) {
	/**
	 * Ora in millisecondi (costante) moltiplicata per l'input dell'utente
	 */
	long ms=(long) (multiplier*hour);
	/**
	 * Ore
	 */
	int h=0;
	/**
	 * Minuti
	 */
	int m=0;
	/**
	 * Secondi
	 */
	int s=0;
	/**
	 * Parte iniziale del messaggio
	 */
	String msg="Il salvataggio avverrà ogni ";
	if (multiplier != 1) {

		s=(int)(ms/1000)%60;
		m=(int)(ms/(1000*60))%60;
		h=(int)(ms/(1000*60*60))%60;
		if(h!=0) msg+=h+" h ";
		if(m!=0) msg+=m+" m ";
		if(s!=0) msg+=s+" s ";
	}
		
	else
		msg += "ora.";
	return msg+"\n";
	
}


}
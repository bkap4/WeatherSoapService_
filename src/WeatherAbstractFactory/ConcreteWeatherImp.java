package WeatherAbstractFactory;

import ConnectionInterface.ContreteConnection;
import ConnectionInterface.MelbourneSoapConnection;
import Models.RainFall;
import Models.Temperature;
import Models.Weathers;

public class ConcreteWeatherImp implements WeatherFactory,WeatherElements{
	private Weathers weather;
	private String location;
	private MelbourneSoapConnection  conn;
	private int indexTime;
	private int indexReading;
	private String server;
	private String [] times;
	private String [] reading;

	public void setConn(MelbourneSoapConnection conn) {
        indexTime= 0;
        indexReading= 1;
		if (conn == null) {
            this.conn = new ContreteConnection(location);
            server = "Melbourne";
        }else{
            this.conn =  conn;
                server = "Timelapse";
        }

	}
	public ConcreteWeatherImp(String location) {
		this.location = location;
		//conn = new ContreteConnection (location);
		//setConn(con);
	}
	@Override
	public Weathers createrWeather(String element) {
	    int lapseRainIndex = 0;
	    if (server.equals("Timelapse")){
            lapseRainIndex= 1;
        }
		if (element.equalsIgnoreCase("All")){

			Temperature temp = new Temperature (temperatureReading()[indexTime],temperatureReading()[indexReading]);
			RainFall rain = new RainFall (rainfallReadings()[indexTime],rainfallReadings()[indexReading+lapseRainIndex]);
			weather = new Weathers(temp,rain,location);
		}else if (element.equalsIgnoreCase("Rainfall")){
			RainFall rain = new RainFall (rainfallReadings()[indexTime],rainfallReadings()[indexReading+lapseRainIndex]);
			weather = new Weathers(rain,location);
		}else {
			Temperature temp = new Temperature (temperatureReading ()[indexTime],temperatureReading ()[indexReading]);
			weather = new Weathers(temp,location);
		}
		return weather;
	}
	@Override
	public String [] rainfallReadings() {
		 times =conn.geWeathertReading("rainfall");
		 return times;
	}
	@Override
	public String [] temperatureReading () {
		reading = conn.geWeathertReading("temperature");
		return reading;

	}

	@Override
	public String[] timeLapseWeather() {
		System.out.println(conn.geWeathertReading("Time")[0]);
		System.out.println(conn.geWeathertReading("Temperature")[1]);
		System.out.println(conn.geWeathertReading("Rainfall")[2]);
		return new String[0];
	}



}

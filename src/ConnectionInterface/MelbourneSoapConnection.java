package ConnectionInterface;

import melbourneweather2.MelbourneWeather2Stub;

public interface MelbourneSoapConnection {
	
	MelbourneWeather2Stub setWeatherConnection ();
	String [] geWeathertReading(String element);
}

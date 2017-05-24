package WeatherAbstractFactory;


import Models.Weathers;

public interface WeatherFactory {
	Weathers createrWeather (String elements);
}

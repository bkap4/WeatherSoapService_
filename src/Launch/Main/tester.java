package Launch.Main;
import java.lang.Exception;

import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.*;

/*
 * Basic example of use of MelbourneWeather2 Axis2 web services in Java
 * IMPORTANT: This is not intended as an example of good design. It is
 * simply an illustration of the basics.
 * 
 * Author:  David.Squire@monash.edu
 * Last Modified: 20170405
 */		

public class tester {

	// set up some constants to index into the result arrays
	private static final int TimestampIndex = 0;
	private static final int RainfallIndex = 1;
	private static final int TemperatureIndex = 1;
	
	public static void main(String[] args) throws Exception {

		final MelbourneWeather2Stub melbourneWeatherService = new MelbourneWeather2Stub();
		
		// Get the available locations from the web service
		GetLocationsResponse LocationsResponse = melbourneWeatherService.getLocations();
		String[] Locations = LocationsResponse.get_return();
		
		// Loop over the locations, and display the temperature and rainfall at each
		for (int i = 0; i < Locations.length; i++) {
			// Get rainfall
			GetRainfall RainfallRequest = new GetRainfall();
			RainfallRequest.setLocation(Locations[i]);
			GetRainfallResponse RainfallResponse = melbourneWeatherService.getRainfall(RainfallRequest);
			String[] Rainfall = RainfallResponse.get_return();
			// Get temperature
			GetTemperature TemperatureRequest = new GetTemperature();
			TemperatureRequest.setLocation(Locations[i]);
			GetTemperatureResponse TemperatureResponse = melbourneWeatherService.getTemperature(TemperatureRequest);
			String[] Temperature = TemperatureResponse.get_return();
			System.out.print(
				Locations[i]
				+ " @ " + Rainfall[TimestampIndex]
				+ ":\n\tTemperature:\t" + Temperature[TemperatureIndex]
				+ "\n\tRainfall:\t" + Rainfall[RainfallIndex]
				+ "\n\n"
			);
		}	
	}

}
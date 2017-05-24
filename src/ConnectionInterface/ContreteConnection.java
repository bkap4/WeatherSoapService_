package ConnectionInterface;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.GetRainfall;
import melbourneweather2.MelbourneWeather2Stub.GetRainfallResponse;
import melbourneweather2.MelbourneWeather2Stub.GetTemperature;
import melbourneweather2.MelbourneWeather2Stub.GetTemperatureResponse;

public class ContreteConnection implements MelbourneSoapConnection {
	private String location;
	private  MelbourneWeather2Stub melbourneWeatherService;
	
	public ContreteConnection(String location) {
		this.location = location;
		setWeatherConnection();
	}

	@Override
	public MelbourneWeather2Stub setWeatherConnection() {
		//MelbourneWeather2Stub melbourneWeatherService = null;
		try {
			melbourneWeatherService = new MelbourneWeather2Stub ();
			
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return melbourneWeatherService;
	}

	
	@Override
	public String[] geWeathertReading (String element) {
		String [] readings = null;
		switch (element.toLowerCase()){
		case "rainfall":
			GetRainfall RainfallRequest = new GetRainfall();
			RainfallRequest.setLocation(location);
			GetRainfallResponse RainfallResponse;
			try {
				RainfallResponse = melbourneWeatherService.getRainfall(RainfallRequest);
				readings =  RainfallResponse.get_return();
				System.out.println("Rain service "+readings[0]+  " " + readings[1]);
			} catch (RemoteException | ExceptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "temperature":
			GetTemperature TemperatureRequest = new GetTemperature();
			TemperatureRequest.setLocation(location);
			GetTemperatureResponse TemperatureResponse;
			try {
				TemperatureResponse = melbourneWeatherService.getTemperature(TemperatureRequest);
				readings = TemperatureResponse.get_return();
				System.out.println("Temp service "+readings[0]+  " " + readings[1]);
			} catch (RemoteException | ExceptionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			break;
		}
		return readings;
	}


}

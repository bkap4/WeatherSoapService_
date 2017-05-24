package ConnectionInterface;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import melbourneweathertimelapse.ExceptionException;
import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub;

public class ContreteTimelapseConnection implements  MelbourneSoapTimelapse {
	private MelbourneWeatherTimeLapseStub timelapseStub;
	private String location;

    public MelbourneWeatherTimeLapseStub getTimelapseStub() {
        return timelapseStub;
    }

    public void setTimelapseStub(MelbourneWeatherTimeLapseStub timelapseStub) {

        this.timelapseStub = timelapseStub;
    }

    public String getLocation() {

        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }

    public ContreteTimelapseConnection(String loaction) {
        this.location = loaction;
        setTimelapseConnection();
    }
    @Override
	public MelbourneWeatherTimeLapseStub setTimelapseConnection() {
		System.out.println("Time laps stub");
		try {
			timelapseStub = new MelbourneWeatherTimeLapseStub();
		} catch (AxisFault e) {
			e.printStackTrace();
		}
		return timelapseStub;
	}
	@Override
	public String[] timeLapseReading() {
        String readings [] = null;
		melbourneweathertimelapse.MelbourneWeatherTimeLapseStub.GetLocationsResponse LocationsResponse = null;
        MelbourneWeatherTimeLapseStub.GetWeather weather = new MelbourneWeatherTimeLapseStub.GetWeather();
        weather.setLocation(location);
        try {
            MelbourneWeatherTimeLapseStub.GetWeatherResponse weatherResponse = timelapseStub.getWeather(weather);
            readings =  weatherResponse.get_return();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ExceptionException e) {
            e.printStackTrace();
        }
		return readings;
	}

}

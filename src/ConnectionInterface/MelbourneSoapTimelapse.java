package ConnectionInterface;

import melbourneweathertimelapse.MelbourneWeatherTimeLapseStub;

public interface MelbourneSoapTimelapse {
	MelbourneWeatherTimeLapseStub setTimelapseConnection();
	String [] timeLapseReading ();
}

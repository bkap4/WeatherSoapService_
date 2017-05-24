package ConnectionInterface;

import melbourneweather2.MelbourneWeather2Stub;

/**
 * Created by grayk on 5/20/2017.
 */
public class ConnectionAdaptor implements MelbourneSoapConnection{
    private MelbourneSoapTimelapse timelapseConnection;
    private String loaction;

    public ConnectionAdaptor(String loaction) {
        this.loaction = loaction;
    }

    public void setTimelapseConnection(MelbourneSoapTimelapse timelapseConnection) {
        this.timelapseConnection = timelapseConnection;
    }

    @Override
    public MelbourneWeather2Stub setWeatherConnection() {
        return null;
    }

    @Override
    public String[] geWeathertReading(String element) {
        timelapseConnection = new ContreteTimelapseConnection(loaction);
        return timelapseConnection.timeLapseReading();
    }


}

package Controller.Handler;

import java.rmi.RemoteException;




import Models.Weathers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ViewWeatherController {
	private ObservableList <Weathers>  weather;
	
	public ViewWeatherController (Weathers location) throws RemoteException{
		weather = FXCollections.observableArrayList();
		weather.add(location);
		
	}
	
	public ObservableList<Weathers> getWeather() {
		return weather;
	}

	public void setWeather(ObservableList<Weathers> weather) {
		this.weather = weather;
	}



	


	

}

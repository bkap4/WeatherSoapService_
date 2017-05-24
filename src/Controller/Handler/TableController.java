package Controller.Handler;

import java.rmi.RemoteException;
import Models.Weathers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import melbourneweather2.ExceptionException;

public class TableController {
	private Weathers location;
	private ViewWeatherController view;

	public TableController(Weathers location) throws RemoteException, ExceptionException {
		this.location = location;
		view = new ViewWeatherController(location) ;
	}
	
	public Weathers getLocation() {
		return location;
	}

	public void setLocation(Weathers location) throws RemoteException, ExceptionException {
		ObservableList <Weathers>  weather = FXCollections.observableArrayList();
		this.location = location;
		weather.add(location);		//view = new ViewWeatherController(location);
		view.setWeather(weather);

	}

	public ViewWeatherController getView() {
		return view;
	}

	public void setView(ViewWeatherController view) {
		this.view = view;
	}

	public ObservableList <Weathers> poulateTable(){
		ObservableList <Weathers>  weather = FXCollections.observableArrayList();
		weather = view.getWeather();
		return weather;	
	}

}

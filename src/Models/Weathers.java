package Models;

import WeatherObserver.Observer;
import WeatherObserver.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;


public class Weathers extends TimerTask implements Subject{
	private String location;
	private String rainfallReading;
	private String rainfallTimeStamp;
	private String temReading;
	private String tempTimeStamp;
	private Temperature temp;
	private RainFall rain;
	private List <Observer> listOfObserver = new ArrayList<Observer>();
	
	public Weathers(Temperature temp, RainFall rain, String location) {
		this.temp = temp;
		this.rain = rain;
		this.location = location;
		System.out.println("__________________GB_________________________");
		System.out.println("in weather object rain " + rain.getReading() + " " + rain.getTimestamp());
		System.out.println("in weather object temp  " + temp.getReading() + " " + temp.getTimestamp());
	}
	
	public Weathers(Temperature temp, String location) {
		this.temp = temp;
		this.location = location;
	}
	

	public Weathers(RainFall rain, String location) {
		this.rain = rain;
		this.location = location;
	}
	
	public String getRainfallReading() {
		return rainfallReading;
	}

	public void setRainfallReading() {
		this.rainfallReading = getRain().getReading();
	}

	public String getRainfallTimeStamp() {
		return rainfallTimeStamp;
	}

	public void setRainfallTimeStamp() {
		this.rainfallTimeStamp = getRain().getTimestamp();
	}

	public String getTemReading() {
		return temReading;
	}

	public void setTemReading() {
		this.temReading = getTemp().getReading();
	}

	public String getTempTimeStamp() {
		return tempTimeStamp;
	}

	public void setTempTimeStamp() {
		this.tempTimeStamp = getTemp().getTimestamp();
		
	}
	public Temperature getTemp() {
		return temp;
	}

	public void setTemp(Temperature temp) {
		this.temp = temp;
	}

	public RainFall getRain() {
		return rain;
	}

	public void setRain(RainFall rain) {
		this.rain = rain;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<Observer> getListOfObserver() {
		return listOfObserver;
	}

	public void setListOfObserver(List<Observer> listOfObserver) {
		this.listOfObserver = listOfObserver;
	}

	

	@Override
	public void registerLocation(Observer obs) {
		listOfObserver.add(obs);
		System.out.println("adding  ... " +listOfObserver.size());
		
	}

	@Override
	public void removeLocation(Observer obs) {
		listOfObserver.remove(obs);
		System.out.println("detaching  ... " +listOfObserver.size());
		setListOfObserver(listOfObserver);
	}

	@Override
	public void notifyObserver() {
		System.out.println("observing ... " +listOfObserver.size());
		if (listOfObserver.size() >= 1){
			for (Observer lis: listOfObserver){
				
				lis.updateObserver();
			}
		}
		
		}

	@Override
	public void run() {
		notifyObserver();
	}
	


}

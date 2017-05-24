package WeatherObserver;

public interface Subject {
	public void registerLocation(Observer obs);
	public void  removeLocation (Observer obs);
	public void notifyObserver();

	
}

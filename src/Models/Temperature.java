package Models;



public class Temperature {
	private String timestamp;
	private String reading;
	
	public Temperature(String timestamp, String reading) {
		this.timestamp = timestamp;
		this.reading = reading;
		System.out.println("in temp object" + timestamp + " " + reading  );
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getReading() {
		return reading;
	}

	public void setReading(String reading) {
		this.reading = reading;
	}
	



}

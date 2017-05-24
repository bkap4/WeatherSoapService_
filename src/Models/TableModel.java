package Models;

import javafx.scene.control.TableColumn ;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableModel  {
	private TableColumn <Weathers,String> loactionName ;
	private TableColumn <Weathers,String> rainfal ;
	private TableColumn <Weathers,String> rainfalTimeStamp;
	private TableColumn <Weathers,String> temp;
	private TableColumn <Weathers,String> time;
	
	private int locColWidth;
	private int rainColWidth;
	private int rainTimeColWidth;
	private int trempColWidth;
	private int timeTempColWidth;
	private TableView <Weathers> table;
	
	
	
	public TableModel(String loc,String rain,String tRain,String temp,String tTime){
		loactionName = new TableColumn <>(loc);
		rainfal = new TableColumn <>(rain);
		rainfalTimeStamp= new TableColumn <>(tRain);
		this.temp = new TableColumn <>(temp);
		time = new TableColumn <>(tTime);
		
	}
	public TableModel(String loc,String rain,String tRain,int t){
		if (t==1){
			loactionName = new TableColumn <>(loc);
			rainfal = new TableColumn <>(rain);
			rainfalTimeStamp= new TableColumn <>(tRain);
		}else{
			loactionName = new TableColumn <>(loc);
			temp = new TableColumn <>(rain);
			time= new TableColumn <>(tRain);
		}
		
	}
	public void setAllColumnWidths(int locColWidth, int rainColWidth, int rainTimeColWidth, int trempColWidth, int timeTempColWidth){	
		loactionName.setMinWidth(locColWidth);
		rainfal.setMinWidth(rainColWidth);
		rainfalTimeStamp.setMinWidth(rainTimeColWidth);
		temp.setMinWidth(trempColWidth);
		time.setMinWidth(timeTempColWidth);	
		this.locColWidth = locColWidth ;
		this.rainColWidth = rainColWidth;
		this.rainTimeColWidth=rainTimeColWidth;
	 	this.trempColWidth=trempColWidth;
		this.timeTempColWidth=timeTempColWidth;		
	}
	public void setAllColumnWidths(int locColWidth, int rainColWidth, int rainTimeColWidth,int n){	
		if (n==1){
			loactionName.setMinWidth(locColWidth);
			rainfal.setMinWidth(rainColWidth);
			rainfalTimeStamp.setMinWidth(rainTimeColWidth);
			this.locColWidth = locColWidth ;
			this.rainColWidth = rainColWidth;
			this.rainTimeColWidth=rainTimeColWidth;	
			
		}else{ 
			temp.setMinWidth(trempColWidth);
			time.setMinWidth(timeTempColWidth);	
			this.locColWidth = locColWidth ;
			this.rainColWidth = rainColWidth;
			this.rainTimeColWidth=rainTimeColWidth;	
			
		}
		/*loactionName.setMinWidth(locColWidth);
		rainfal.setMinWidth(rainColWidth);
		rainfalTimeStamp.setMinWidth(rainTimeColWidth);
		//temp.setMinWidth(trempColWidth);
		//time.setMinWidth(timeTempColWidth);	
		this.locColWidth = locColWidth ;
		this.rainColWidth = rainColWidth;
		this.rainTimeColWidth=rainTimeColWidth;	*/
	}
	
	public void setPropertyValue(String loc,String rReading,String rTime, String aTime,String tStamp){
		loactionName.setCellValueFactory(new PropertyValueFactory<>(loc));
		rainfal.setCellValueFactory(new PropertyValueFactory<>(rReading));
		rainfalTimeStamp.setCellValueFactory(new PropertyValueFactory<>(rTime));
		temp.setCellValueFactory(new PropertyValueFactory<>(aTime));
		time.setCellValueFactory(new PropertyValueFactory<>(tStamp));
	}
	
	public void setPropertyValueRain(String loc,String rReading,String rTime){
		loactionName.setCellValueFactory(new PropertyValueFactory<>(loc));
		rainfal.setCellValueFactory(new PropertyValueFactory<>(rReading));
		rainfalTimeStamp.setCellValueFactory(new PropertyValueFactory<>(rTime));
	}
	public void setPropertyValueTemp(String loc,String rReading,String rTime){
		loactionName.setCellValueFactory(new PropertyValueFactory<>(loc));
		temp.setCellValueFactory(new PropertyValueFactory<>(rReading));
		time.setCellValueFactory(new PropertyValueFactory<>(rTime));
	}
	public TableView<Weathers> getAllTable() {
		table = new TableView<>();
		table.getColumns().addAll(loactionName,rainfal,rainfalTimeStamp,temp,time);
		return table;
	}
	public TableView<Weathers> getPartOFTableRain() {
		table = new TableView<>();
		table.getColumns().addAll(loactionName,rainfal,rainfalTimeStamp);
		return table;
	}
	public TableView<Weathers> getPartOFTableTemp() {
		table = new TableView<>();
		table.getColumns().addAll(loactionName,temp,time);
		return table;
	}
	public void setTable(TableView<Weathers> table) {
		this.table = table;
	}
	public TableColumn<Weathers, String> getLoactionName() {
		return loactionName;
	}
	public void setLoactionName(TableColumn<Weathers, String> loactionName) {
		this.loactionName = loactionName;
	}
	public TableColumn<Weathers, String> getRainfal() {
		return rainfal;
	}
	public void setRainfal(TableColumn<Weathers, String> rainfal) {
		this.rainfal = rainfal;
	}
	public TableColumn<Weathers, String> getRainfalTimeStamp() {
		return rainfalTimeStamp;
	}
	public void setRainfalTimeStamp(TableColumn<Weathers, String> rainfalTimeStamp) {
		this.rainfalTimeStamp = rainfalTimeStamp;
	}
	public TableColumn<Weathers, String> getTemp() {
		return temp;
	}
	public void setTemp(TableColumn<Weathers, String> temp) {
		this.temp = temp;
	}
	public TableColumn<Weathers, String> getTime() {
		return time;
	}
	public void setTime(TableColumn<Weathers, String> time) {
		this.time = time;
	}
	public int getLocColWidth() {
		return locColWidth;
	}
	public void setLocColWidth(int locColWidth) {
		this.locColWidth = locColWidth;
	}
	public int getRainColWidth() {
		return rainColWidth;
	}
	public void setRainColWidth(int rainColWidth) {
		this.rainColWidth = rainColWidth;
	}
	public int getRainTimeColWidth() {
		return rainTimeColWidth;
	}
	public void setRainTimeColWidth(int rainTimeColWidth) {
		this.rainTimeColWidth = rainTimeColWidth;
	}
	public int getTrempColWidth() {
		return trempColWidth;
	}
	public void setTrempColWidth(int trempColWidth) {
		this.trempColWidth = trempColWidth;
	}
	public int getTimeTempColWidth() {
		return timeTempColWidth;
	}
	public void setTimeTempColWidth(int timeTempColWidth) {
		this.timeTempColWidth = timeTempColWidth;
	}
	
	
	
	
	
	
	

}

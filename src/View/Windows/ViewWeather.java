package View.Windows;

import java.rmi.RemoteException;

import ConnectionInterface.ConnectionAdaptor;
import Controller.Handler.TableController;
import Models.TableModel;
import Models.Weathers;
import WeatherAbstractFactory.ConcreteWeatherImp;
import WeatherObserver.Observer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import melbourneweather2.ExceptionException;

public class ViewWeather  implements Observer{
	private Stage window ;
	private Scene scene;
	private TableModel model;
	private TableController tableControl;
	private TableView table; 
	private VBox layout;
	private Weathers location;
	private String element;
	private String server;
    private final CategoryAxis xAxis = new CategoryAxis();
    private final NumberAxis yAxis = new NumberAxis();
    private final LineChart<String, Number> lineChart  = new LineChart<String, Number>(xAxis, yAxis);
    private  XYChart.Series series = new XYChart.Series();
    private XYChart.Series series2 = new XYChart.Series();
	
	public ViewWeather (Weathers weathers,String element,String server) throws RemoteException, ExceptionException {
        this.server = server;
        this.location = weathers;
        this.element = element;
        location.registerLocation(this);
        window = new Stage();
        window.setTitle(location.getLocation());
        model = getTableModel(element);
        if (element.equals("All")) {
            table = model.getAllTable();
            setDataGraph(weathers);
        } else if (element.equals("Rainfall")) {
            table = model.getPartOFTableRain();
            setDataGraph(weathers);
        } else if (element.equals("Temperature")) {
            table = model.getPartOFTableTemp();
            setDataGraph(weathers);
        }
        tableControl = new TableController(location);
        table.setItems(tableControl.poulateTable());
        Button button = new Button("Detact");
        button.setOnAction(e -> {
            location.removeLocation(this);
        });
        layout = new VBox(10);
        layout.getChildren().addAll(table);
        layout.getChildren().addAll(lineChart);
        layout.getChildren().addAll(button);
        if (element.equals("All")) {
            scene = new Scene(layout, 550, 250);
        } else {
            scene = new Scene(layout, 350, 250);
        }
        layout.setAlignment(Pos.TOP_CENTER);
        lineChart.getData().add(series);
        lineChart.getData().add(series2);
        window.setScene(scene);
        window.show();
    }


	@Override
	public void updateObserver() {
		//MelbourneSoapTimelapse timeee = new ContreteTimelapseConnection(location.getLocation());
		ConnectionAdaptor con = new ConnectionAdaptor(location.getLocation());
		ConcreteWeatherImp imp = new ConcreteWeatherImp(location.getLocation());
		if (server.equals("Melbourne Timelapse"))
			imp.setConn(con);
		else
			imp.setConn(null);
		try {
			switch(element){
			case "All":
				Weathers weth = imp.createrWeather("All");
				location.setLocation(weth.getLocation());
				location.setRain(weth.getRain());
				location.setTemp(weth.getTemp());
				location.setRainfallReading();
				location.setRainfallTimeStamp();
				location.setTemReading();
				location.setTempTimeStamp();
				tableControl.setLocation(location);
				model.setPropertyValue("location","rainfallReading","rainfallTimeStamp","temReading","tempTimeStamp");
				table.getItems().clear();
				table.getItems().addAll(tableControl.poulateTable());
                setDataGraph(weth);
				break;
			case "Rainfall":
				Weathers wethh = imp.createrWeather(element);
				location.setLocation(wethh.getLocation());
				location.setRain(wethh.getRain());
				location.setRainfallReading();
				location.setRainfallTimeStamp();
				tableControl.setLocation(location);
				model.setPropertyValueRain("location","rainfallReading","rainfallTimeStamp");
				table.getItems().clear();
				table.getItems().addAll(tableControl.poulateTable());
                setDataGraph(wethh);
				break;
			case "Temperature":
				Weathers wethhh = imp.createrWeather(element);
				location.setLocation(wethhh.getLocation());
				location.setTemp(wethhh.getTemp());
				location.setTemReading();
				location.setTempTimeStamp();
				tableControl.setLocation(location);
				model.setPropertyValueTemp("location","temReading","tempTimeStamp");
				table.getItems().clear();
				table.getItems().addAll(tableControl.poulateTable());
                setDataGraph(wethhh);
				break;
			}
		} catch (RemoteException | ExceptionException e) {
			e.printStackTrace();
		}
		
	}
	
	public TableModel getTableModel (String model){
		System.out.println("___________Table view______________________");
		TableModel models = null;
		switch(model){
		case "All":
			models = new TableModel("Location","Rainfall","Rain Timestamp", "Temperature", "Temp timestamp");
			models.setAllColumnWidths(100, 100, 120, 100, 120);
			location.setRainfallReading();
			location.setRainfallTimeStamp();
			location.setTemReading();
			location.setTempTimeStamp();
			models.setPropertyValue("location","rainfallReading","rainfallTimeStamp","temReading","tempTimeStamp");
			break;
		case "Rainfall":
			models = new TableModel("Location","Rainfall","Rain Timestamp",1);
			models.setAllColumnWidths(100, 100, 120,1);
			location.setRainfallReading();
			location.setRainfallTimeStamp();
			models.setPropertyValueRain("location","rainfallReading","rainfallTimeStamp");
			break;
		case "Temperature":
			models = new TableModel("Location", "Temperature", "Temp timestamp",0);
			models.setAllColumnWidths(100, 100, 120,0);
			location.setTemReading();
			location.setTempTimeStamp();
			models.setPropertyValueTemp("location","temReading","tempTimeStamp");
			break;
			
		}
		return models;
		
	}
	public void setDataGraph (Weathers weathers){
	    switch (element){
	        case "All":
                String tempTime = weathers.getTemp().getTimestamp();
                double tempReading = Double.parseDouble(weathers.getTemp().getReading());
                String rainTime = weathers.getRain().getTimestamp();
                double rainReading = Double.parseDouble(weathers.getRain().getReading());
                lineChart.setTitle("Readings for " + weathers.getLocation() + " From "+ server + " server");
                series.setName("Temperature");
                series2.setName("Rainfall");
                series.getData().add(new XYChart.Data(tempTime, tempReading));
                series2.getData().add(new XYChart.Data(rainTime,rainReading));
                break;
            case "Rainfall":

                String _rainTime = weathers.getRain().getTimestamp();
                double _rainReading = Double.parseDouble(weathers.getRain().getReading());
                lineChart.setTitle("Readings for " + weathers.getLocation() + " From "+ server + " server");
                //series.setName("Temperature");
                series2.setName("Rainfall");
                //series.getData().add(new XYChart.Data(time, reading));
                series2.getData().add(new XYChart.Data(_rainTime,_rainReading));

                break;
            case "Temperature":
                String _rainTime_ = weathers.getTemp().getTimestamp();
                double _rainReading_ = Double.parseDouble(weathers.getTemp().getReading());
                lineChart.setTitle("Readings for " + weathers.getLocation() + " From "+ server + " server");
                series.setName("Temperature");
                //series2.setName("Rainfall");
                series.getData().add(new XYChart.Data(_rainTime_, _rainReading_));
                //series2.getData().add(new XYChart.Data(_rainTime,_rainReading));


                break;


        }

    }
}

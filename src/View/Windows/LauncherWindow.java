package View.Windows;

import java.rmi.RemoteException;
import java.util.Timer;

import ConnectionInterface.ConnectionAdaptor;
import ConnectionInterface.ContreteTimelapseConnection;
import ConnectionInterface.MelbourneSoapTimelapse;
import Models.Weathers;
import WeatherAbstractFactory.ConcreteWeatherImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import melbourneweather2.ExceptionException;
import melbourneweather2.MelbourneWeather2Stub;
import melbourneweather2.MelbourneWeather2Stub.GetLocationsResponse;


public class LauncherWindow {
	private Stage window;
	private ToggleGroup group;
	private String weatherChoice="All";
	public LauncherWindow(String title) throws RemoteException, ExceptionException{
		
		final MelbourneWeather2Stub MelbourneWeatherService = new MelbourneWeather2Stub();
		group = new ToggleGroup();
		RadioButton button1 = new RadioButton("All");
		button1.setToggleGroup(group);
		button1.setSelected(true);
		RadioButton button2 = new RadioButton("Rainfall");
		button2.setToggleGroup(group);
		RadioButton button3 = new RadioButton("Temperature");
		button3.setToggleGroup(group);
		
		// Get the available locations from the web service
		GetLocationsResponse LocationsResponse = MelbourneWeatherService.getLocations();
		String[] Locations = LocationsResponse.get_return();
		String[] servers = {"Melbourne Soap","Melbourne Timelapse"};
		ChoiceBox <String> choice = new ChoiceBox <String>();
		choice.getItems().addAll(Locations);
		choice.setValue(Locations[0]);

		ChoiceBox <String> server = new ChoiceBox <String>();
		server.getItems().addAll(servers);
		server.setValue(servers[0]);
		
		Button button = new Button ("Check");
		button.setOnAction(e-> {
			try {
				getWeatherReading(choice,server);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ExceptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		FlowPane flowpane = new FlowPane();
		window = new Stage ();
		window.setTitle(title);
		VBox layout = new VBox(10);
		flowpane.setAlignment(Pos.CENTER);
	
		//StackPane layout = new StackPane(); 
		layout.getChildren().add(choice);
		layout.getChildren().add(server);
		flowpane.getChildren().add(button1);
		flowpane.getChildren().add(button2);
		flowpane.getChildren().add(button3);
		layout.getChildren().addAll(flowpane);
		layout.getChildren().add(button);
		
		Scene scene = new Scene (layout,300,150);
		layout.setAlignment(Pos.CENTER);
		button.setOnAction(e-> {
			try {
				getWeatherReading(choice,server);
			} catch (RemoteException | ExceptionException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
		      @Override
			public void changed(ObservableValue<? extends Toggle> ov,
		          Toggle old_toggle, Toggle t1) {
		        if (group.getSelectedToggle() != null) {
		        	 RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); // Cast object to radio button
		             weatherChoice = chk.getText(); 
		        }
		      }
		    });
		window.setScene(scene);
		window.show();		
	}
	private void getWeatherReading(ChoiceBox<String> choice, ChoiceBox<String> servers) throws RemoteException, ExceptionException {

		System.out.println("yeps");
		Timer time = new Timer(); 
		String selection = choice.getValue();
		String toConnect = servers.getValue();
		ConcreteWeatherImp imp = new ConcreteWeatherImp (selection);
		int  updateTime = 0;
		//imp.setConn(null);


        //ContreteTimelapseConnection timee = new ContreteTimelapseConnection(selection);

        //MelbourneSoapTimelapse timeee = new ContreteTimelapseConnection(selection);
        ConnectionAdaptor  con = new ConnectionAdaptor(selection);
        //con.setTimelapseConnection(timee);
        if (toConnect.equals("Melbourne Timelapse")){
            imp.setConn(con);
            updateTime = 2000;
        }else{
            imp.setConn(null);
            updateTime = 300000;
        }


        System.out.println("_____________No adaptor__________________");
		/*System.out.println(timee.timeLapseReading()[0]);
        System.out.println(timee.timeLapseReading()[1]);
        System.out.println(timee.timeLapseReading()[2]);*/
       /* System.out.println("_____________With adaptor__________________");
        System.out.println(con.geWeathertReading("Rainfall")[0]);
        System.out.println(con.geWeathertReading("Rainfall")[1]);
        System.out.println(con.geWeathertReading("Rainfall")[2]);*/


		switch (weatherChoice){
		case "All":
			Weathers weathersAll = imp.createrWeather(weatherChoice);
			time.schedule(weathersAll, 0,updateTime);
			ViewWeather weatherAll = new ViewWeather(weathersAll,weatherChoice, toConnect);
			break;
		case "Rainfall":
			Weathers weatherRain = imp.createrWeather(weatherChoice);
			time.schedule(weatherRain, 0,updateTime);
			ViewWeather weatherRainfall = new ViewWeather(weatherRain,weatherChoice,toConnect);
			break;
		case "Temperature":
			Weathers weatherTemp = imp.createrWeather(weatherChoice);
			time.schedule(weatherTemp, 0, updateTime);
			ViewWeather weathersTemp = new ViewWeather(weatherTemp,weatherChoice,toConnect);
			break;
		
		}
		
		//weathers.registerLocation(weather);
	}

}

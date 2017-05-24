package Launch.Main;


import ConnectionInterface.ContreteTimelapseConnection;
import View.Windows.LauncherWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class WeatherDriver extends Application {
	public static void main(String[] args) {
		launch(args);
		
		
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");

		LauncherWindow launch = new LauncherWindow("Melbourne Weather 2"); 
	}


}

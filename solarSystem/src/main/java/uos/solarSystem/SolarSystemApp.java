package uos.solarSystem;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import uos.solarSystem.Controller.Controller;
import uos.solarSystem.Model.Model;
import uos.solarSystem.View.View;

/**
 * Simulation of a solar system. Its purpose is to present some basic concepts of gravity, 
 * including orbit shape and travel speed, depending on distance from the main source of gravity, 
 * such as stars or planets (in case of orbiting moons).
 * @author Michal Brenda, 00559233 University of Salford.
 *
 */
public class SolarSystemApp extends Application{

	private static SolarSystemApp instance;
	Model model;
	View view;
	Controller controller;
	
	public void start(Stage primaryStage) throws Exception {
		instance = this;
		
		GridPane root = new GridPane();

		model = new Model();
		view = new View(root, model, primaryStage);
		controller = new Controller(root, model, view);
		primaryStage.setTitle("Solar System Simulation");
		primaryStage.setScene(new Scene(root, 1280,900));
		
		primaryStage.show();
	}

	public static void main(String[] args) {launch(args);}

	public static SolarSystemApp getInstance() {
		return instance;
	}

}

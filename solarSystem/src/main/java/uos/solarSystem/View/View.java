package uos.solarSystem.View;

import java.util.ArrayList;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import uos.solarSystem.Model.GameObject;
import uos.solarSystem.Model.Model;

public class View {
	//GridPane used to allow some CSS-like rules into aligning all elements in the view
	GridPane root;
	//StackPane used because the center of the pane is 0,0
	StackPane simulationPane;
	ControlsPane controlsPane;
	InformationPane informationPane;
	Model model;
//	Button infoButton;
	
	//used to project a potential orbit when creating a new celestial body
	Ellipse newPlanetOrbit;
	
	
	public View(GridPane root, Model model, Stage primaryStage) {
		super();
		this.root = root;
		this.model = model;
		controlsPane = new ControlsPane();
		informationPane = new InformationPane(this.model);
		
		newPlanetOrbit = new Ellipse();
		newPlanetOrbit.setStrokeWidth(0.7);
		newPlanetOrbit.setStroke(Color.ORANGE);
		newPlanetOrbit.setFill(Color.TRANSPARENT);
		newPlanetOrbit.setMouseTransparent(true);

		
		root.setAlignment(Pos.CENTER);
		root.setHgap(7);
		root.setVgap(7);
		root.setPadding(new Insets(5,5,5,5));
		
		//Create a simulation pane
		simulationPane = new StackPane();
		Image simulationBackgroundImage = new Image((this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/ivana-cajina-asuyh-_ZX54-unsplash.jpg")));
		root.setBackground(new Background(new BackgroundImage(simulationBackgroundImage, 
																																		BackgroundRepeat.REPEAT, 
																																		BackgroundRepeat.REPEAT, 
																																		BackgroundPosition.CENTER ,
																																		BackgroundSize.DEFAULT)));
//		Image controlsPaneBackgroundImage = new Image((this.getClass().getResourceAsStream("Picture1.png")));
		LinearGradient linearGrad = new LinearGradient( 0.9, 0, 0,10,true,  CycleMethod.NO_CYCLE, 
																								new Stop(0.1f, Color.rgb(0,0,0, .1)),
																								new Stop(0.3f, Color.rgb(255, 255, 255, .8)),
																								new Stop(1.0f, Color.rgb(255, 255, 255, 1)));
		controlsPane.getPane().setBackground(new Background(new BackgroundFill(linearGrad, null, null)));
		simulationPane.setPrefSize(794,468);
		checkSimWindowSize();
		
//		//Add button that shows the information panel on the right
//		infoButton = new Button("☰");
//		infoButton.setBackground(null);
//		infoButton.setTextFill(Color.WHITE);
//		infoButton.setFont(new Font(20));
//		StackPane.setAlignment(infoButton, Pos.TOP_RIGHT);
		//Add planets and the sun to the simulation pane
		addPlanets(model.getObjects());
		simulationPane.getChildren().add(newPlanetOrbit);
//		simulationPane.getChildren().add(infoButton);
		
		//Add all panes to the main window
		root.add(controlsPane.getPane(), 0,1);
		root.add(informationPane.getPane(),1, 0, 1, 2);
		root.add(simulationPane, 0, 0);


	}
	/*
	 * Adds all objects to the simulation pane, along with their orbits. 
	 */
	private void addPlanets(ArrayList<GameObject> objects ) {
		for(GameObject object : objects) {
			//add all visual elements of planets and stars onto the simulation pane
			simulationPane.getChildren().add(object.getOrbitalPath());
			simulationPane.getChildren().add(object.getObjectBody());
		}
	}
	public ControlsPane getControlsPane() {
		return controlsPane;
	}
	public InformationPane getInformationPane() {
		return informationPane;
	}
	public StackPane getSimulationPane() {
		return simulationPane;
	}
	
	/**
	 * Draw orbit on the screen when adjusting it with sliders from Controls pane.
	 * @param width
	 * @param height
	 * @param translateX - location of the orbit on X axis
	 * @param translateY - location of the orbit on Y axis
	 */
	public void drawOrbit(double width, double height, double translateX, double translateY) {
		Ellipse orbit = new Ellipse();
		orbit.setStrokeWidth(1);
		orbit.setStroke(Color.RED);
		orbit.setFill(Color.TRANSPARENT);
		orbit.setRadiusY(height);
		orbit.setRadiusX(width);
		Translate translate = new Translate();
		translate.setX( translateX);
		translate.setY(translateY);
		orbit.getTransforms().add(translate);
		simulationPane.getChildren().add(orbit);
		
	}
	
	public void explosionAnimation(double locationX, double locationY/*, int explosionType*/) {
		
//		Image explosion = ;
//		
//		switch(explosionType) {
//		case 1: 
			 Image explosion = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/explosionRing2.png"));
//			break;
//		case 2: 
//			explosion = new Image(this.getClass().getResourceAsStream("explosionRing.png"));
//			break;
//		}
			
		ImageView explosionView = new ImageView(explosion);
		explosionView. setTranslateX(locationX);
		explosionView. setTranslateY(locationY);
		
		//Animate explosion - by making 
		ScaleTransition trans = new ScaleTransition(Duration.millis(500),explosionView);
		trans.setFromX(0);
		trans.setToX(1); 
		trans.setFromY(0);
		trans.setToY(1);
		trans.setCycleCount(2);
		trans.setAutoReverse(true);
		trans.play();
		RotateTransition rot = new RotateTransition(Duration.millis(800), explosionView);
		rot.setToAngle(720);
		rot.setCycleCount(4);
		rot.play();
		simulationPane.getChildren().add(explosionView);
	}
	//Draws a temporary orbit for a new planet to be placed in the simulation
	public Ellipse getNewPlanetOrbit() {
		return newPlanetOrbit;
	}
	public void setNewPlanetOrbit(Ellipse ellipse) {
		this.newPlanetOrbit = ellipse;
	}
	public void addPlanet(GameObject object) {
		simulationPane.getChildren().add(object.getOrbitalPath());
		simulationPane.getChildren().add(object.getObjectBody());
	}
	public void removeObject(GameObject object) {
		removeItem(object.getObjectBody());
		removeItem(object.getOrbitalPath());
	}
	
	public void removeAllObjects() {
		for(GameObject object : model.getObjects()) {
			removeObject(object);
		}
	}
	public void addItem(Node node) {
		simulationPane.getChildren().add(node);
	}
	public void removeItem(Node node) {
		simulationPane.getChildren().remove(node);
	}

	//Method that adjusts the size of the simulationPane based on the size of the main window
	public void checkSimWindowSize() {
		root.widthProperty().addListener((obs, oldVal, newVal) -> {
	        if(newVal != oldVal){
	        	simulationPane.setMinWidth((double)newVal- 250);
	        }
	    });
		root.heightProperty().addListener((obs, oldVal, newVal) -> {
	        if(newVal != oldVal){
	        	simulationPane.setMinHeight((double) newVal -150);

	        }

	    });
	}
	

	
	
	
	

}

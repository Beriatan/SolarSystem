package uos.solarSystem.Controller;


import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import uos.solarSystem.Model.Factory;
import uos.solarSystem.Model.GameObject;
import uos.solarSystem.Model.Model;
import uos.solarSystem.View.ControlsPane;
import uos.solarSystem.View.InformationPane;
import uos.solarSystem.View.SoundPlayer;
import uos.solarSystem.View.View;

public class Controller implements EventHandler{
	
	private Model model;
	private View view;
	private ControlsPane controlsPane;
	private InformationPane informationPane;
	private Factory factory;
	private String planetType;
	private SoundPlayer sound;
	
	
	public Controller(GridPane root, Model model, View view) {
		super();
		this.model = model;
		this.view = view;
		sound = new SoundPlayer();
		factory = new Factory();
		controlsPane = view.getControlsPane();
		informationPane = view.getInformationPane();
		planetType = "ice planet";
		informationPane.getButton("Destroy Planet").setOnAction(this);
//		view.getInfoButton().setOnAction(this);
		addControlsPaneListeners();
		makeAllPlanetsClickable();		
		checkCollisions();
	}

	@Override
	public void handle(Event event) {
		
		if(event.getSource() == this.controlsPane.getButton("CreatePlanet")) {
			this.controlsPane.getLabel("TestLabel").setText(planetType + " created");
			
			if(planetType.equals("black hole")) {
				view.removeAllObjects();
				model.removeAllObjects();
				this.controlsPane.getLabel("TestLabel").setText("The Black Hole has sucked everything in!!!");

			}
			model.addObject(factory.createObject(planetType,this.controlsPane.getSlider("Height").getValue(), 
											this.controlsPane.getSlider("Width").getValue(),
											this.controlsPane.getSlider("TranslateX").getValue(),
											this.controlsPane.getSlider("TranslateY").getValue(),
											this.controlsPane.getNameField().getText(),
											this.controlsPane.getSlider("Size").getValue(),
											this.controlsPane.getSlider("Mass").getValue()));	
			//Add planet to the system
			this.view.addPlanet(model.getObjects().get(model.getObjects().size()-1));	
			//Hide temporary orbit
			view.getNewPlanetOrbit().setVisible(false);
			
			makeAllPlanetsClickable();
		}
		if(event.getSource() == this.informationPane.getButton("Destroy Planet")) {
			
				view.explosionAnimation(informationPane.getCurrentlyViewedObject().getObjectBody().getTranslateX(),informationPane.getCurrentlyViewedObject().getObjectBody().getTranslateY());
				view.removeObject(informationPane.getCurrentlyViewedObject());
				model.removeObject(informationPane.getCurrentlyViewedObject());
			
		}
	}
	public void makeAllPlanetsClickable() {
		for(GameObject object : model.getObjects())
		{
			object.getObjectBody().setOnMouseClicked(e-> {
					System.out.println("clicked " + object.getName());
					updateInformationPane(object);
			});
		}
	}
	
	/*
	 * Live updates the status of current planet on the information panel
	 * Displays: planet name, type, distance from star, speed, mass, temperature and radius
	 */
	public void updateInformationPane(GameObject object) {
		
		DecimalFormat threeDecimals = new DecimalFormat("#.000");
		DecimalFormat noDecimals = new DecimalFormat("#");
		informationPane.setCurrentlyViewedObject(object);
		
		 Timeline timeline = new Timeline();
	        timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(6), event -> { 
	        	//
	        	String distance = threeDecimals.format(object.getDistanceFromSun());
	        	view.getInformationPane().getLabel("Distance Placeholder").setText(distance + "mln km");
	        	//Converts the displayed speed from pixels into km/s
	        	String speed = threeDecimals.format(object.getSpeed()*1620);
	        	view.getInformationPane().getLabel("Speed Placeholder").setText(speed + " km/s");
	        	//Display temperature
	        	String temperature = threeDecimals.format(object.getTemperature());
	        	view.getInformationPane().getLabel("Temperature Placeholder").setText(temperature + " C");
	        }));
	        timeline.setCycleCount( Animation.INDEFINITE );
	        timeline.play();
	        if(object.getMass()> 1E6) {
		        view.getInformationPane().getLabel("Mass Placeholder").setText(threeDecimals.format(object.getMass()/1E7) +object.getMassExponent() + " kg");

	        }else  view.getInformationPane().getLabel("Mass Placeholder").setText(threeDecimals.format(object.getMass()) +object.getMassExponent() + " kg");
	        view.getInformationPane().getLabel("Name Placeholder").setText(object.getName());
	        view.getInformationPane().getLabel("Type Placeholder").setText(object.getType());
	        view.getInformationPane().getLabel("Radius Placeholder").setText(noDecimals.format(object.getRadius()*424.73) + "km");
	        
	        view.getInformationPane().setPlanetImage(object.getImage());
	}
	
	//Perform collision checks and animate collision if such happens.
	public void checkCollisions() {
		Timeline timeline = new Timeline();
        timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(100), e -> {
        	//A temporary list to store all object that have collided with another objects
        	ArrayList<GameObject> collidedObjects = new ArrayList<>();
        	
        	//Iterate through all objects to check if collision happened...
        	for(GameObject current : model.getObjects()) {
        		//...with any other object
        		for(GameObject object : model.getObjects()) {
        			//Perform this event for any rocky planets that have crashed
        			if(object != current && !current.getType().equals("star") && !current.getType().equals("black hole")) {        				
        				if(current.getObjectBody().getBoundsInParent().intersects(object.getObjectBody().getBoundsInParent())
        						&& !object.getType().equals("star") &&  !object.getType().equals("black hole")) {
        				
        					//Play explosion animation
        					view.explosionAnimation(object.getObjectBody().getTranslateX(),object.getObjectBody().getTranslateY());
        					view.explosionAnimation(current.getObjectBody().getTranslateX(), current.getObjectBody().getTranslateY());
        				      				
        					//Add collided items to the list of items to be deleted later
        					collidedObjects.add(object);
        					if(!collidedObjects.contains(current)) collidedObjects.add(current);
        					sound.playSound("Explosion", 0.3);
        				}
        			}        		
        		}
        	}
        	//Delete all collided objects
        	for(GameObject object : collidedObjects) {
        		view.removeObject(object);
        		model.removeObject(object);
        	}
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
	}
	
	public void addControlsPaneListeners() {
		controlsPane.getSlider("Height").valueProperty().addListener((ov, old_val, new_val) -> {
			view.getNewPlanetOrbit().setVisible(true);
			view.getNewPlanetOrbit().setRadiusY((double) new_val);
	    });
		controlsPane.getSlider("Width").valueProperty().addListener((ov, old_val, new_val) -> {
			view.getNewPlanetOrbit().setVisible(true);
			view.getNewPlanetOrbit().setRadiusX((double) new_val);
	    });
		
		controlsPane.getSlider("TranslateX").valueProperty().addListener((ov, old_val, new_val) -> {
			view.getNewPlanetOrbit().setVisible(true);
			view.getNewPlanetOrbit().setTranslateX((double) new_val);
	    });
		controlsPane.getSlider("TranslateY").valueProperty().addListener((ov, old_val, new_val) -> {
			view.getNewPlanetOrbit().setVisible(true);
			view.getNewPlanetOrbit().setTranslateY((double) new_val);
	    });
	
		controlsPane.getSlider("Size").valueProperty().addListener((ov, old_val, new_val) -> {
				controlsPane.getImageView().setFitHeight(new_val.doubleValue()*2);
				controlsPane.getImageView().setFitWidth(new_val.doubleValue()*2);


	    });
		controlsPane.getSlider("Mass").valueProperty().addListener((ov, old_val, new_val) -> {
			if(new_val.intValue() < 100) {
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/icePlanet (1).gif")));
				planetType = "ice planet";
			}
			if(new_val.intValue() >= 100 && new_val.intValue() < 300) {
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (2).gif")));
				planetType = "dry planet";
			}
			if(new_val.intValue() >= 300 && new_val.intValue() < 500) {
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (2).gif")));
				planetType = "earth like planet";
			}
			if(new_val.intValue() >= 500 && new_val.intValue() < 600) {
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/lavaPlanet (1).gif")));
				planetType = "lava planet";
			}
			if(new_val.intValue() >= 600 && new_val.intValue() < 700) 	{
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (4).gif")));
				planetType = "gas giant with rings";
			}
			if(new_val.intValue() >= 700 && new_val.intValue() < 900) 	{
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiant (4).gif")));
				planetType = "gas giant";
			}
			if(new_val.intValue() >= 900 && new_val.intValue() < 1000) 	{
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (4).gif")));
				planetType = "star";
			}
			if(new_val.intValue() == 1000) 	{
				controlsPane.setDisplayedObject(new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (4).gif")));
				planetType = "black hole";
			}
			

		});
		controlsPane.getButton("CreatePlanet").setOnAction(this);
	}
	

}

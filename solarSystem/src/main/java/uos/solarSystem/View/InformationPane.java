package uos.solarSystem.View;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uos.solarSystem.Model.GameObject;
import uos.solarSystem.Model.Model;
/**
 * Pane that displays all information about the selected GameObject
 * @author Michal Brenda
 *
 */

public class InformationPane implements PaneInterface{
	GridPane informationPane;
	
	private HashMap<String, Button> buttons;
	private HashMap<String, Label> labels;
	private Image planetImage;
	private Label nameLabel,typeLabel, distanceFromSunLabel, speedLabel, massLabel, temperatureLabel, radiusLabel,
							namePlaceholder, typePlaceholder, distanceFromSunPlaceholder, speedPlaceholder, massPlaceholder, temperaturePlaceholder, radiusPlaceholder;
	private Font labelFont, placeholderFont;
	private ImageView iv;
	private Button destroyPlanet;
	private GameObject currentlyViewedObject;
	
	public InformationPane(Model model) {
		
		currentlyViewedObject = model.getObject(model.getObjects().size() -1);
		
		informationPane = new GridPane();
		informationPane.setHgap(7);
		informationPane.setVgap(7);
		
		buttons = new HashMap<>();
		labels = new HashMap<>();
		informationPane.setMinWidth(250);
		
		labelFont = new Font("Lucida Console", 16);
		placeholderFont = new Font("Lucida Console", 20);
		
		destroyPlanet = new Button("Destroy Object");
		
		nameLabel = new Label("Name");
		typeLabel = new Label("Type");
		distanceFromSunLabel = new Label("Distance From Sun");
		speedLabel = new Label("Speed");
		massLabel = new Label("Mass");
		temperatureLabel = new Label("Temperature");
		radiusLabel = new Label("Radius");
		
		namePlaceholder= new Label("Click on an object");
		typePlaceholder = new Label("----");
		distanceFromSunPlaceholder = new Label("----");
		speedPlaceholder = new Label("----");
		massPlaceholder = new Label("----");
		temperaturePlaceholder = new Label("----");
		radiusPlaceholder = new Label("----");
						
		setLabelFontColour(nameLabel, typeLabel, distanceFromSunLabel, speedLabel, massLabel, temperatureLabel, 
													radiusLabel, namePlaceholder, typePlaceholder,  distanceFromSunPlaceholder, speedPlaceholder, 
													massPlaceholder, temperaturePlaceholder, radiusPlaceholder);
		setLabelFont(nameLabel,typeLabel, distanceFromSunLabel, speedLabel, massLabel, temperatureLabel, radiusLabel);
		setPlaceholderFont(namePlaceholder, typePlaceholder, distanceFromSunPlaceholder, speedPlaceholder, massPlaceholder, temperaturePlaceholder, radiusPlaceholder);
		
		
		String url  = "/uos/solarSystem/View/Graphics/RockyPlanets/defaultPlanet.gif";
		planetImage = new Image(this.getClass().getResourceAsStream(url));
		iv = new ImageView(planetImage);
		
		
		informationPane.add(iv, 1, 1, 2, 6);
		informationPane.add(nameLabel, 1, 10, 4, 1);
		informationPane.add(namePlaceholder, 1, 11, 4, 1);
		informationPane.add(typeLabel, 1, 16, 4, 1);
		informationPane.add(typePlaceholder, 1, 17, 4, 1);
		informationPane.add(distanceFromSunLabel, 1, 23,4, 1);
		informationPane.add(distanceFromSunPlaceholder, 1, 24, 4, 1);
		informationPane.add(speedLabel, 1, 29, 4, 1);
		informationPane.add(speedPlaceholder, 1, 30, 4, 1);
		informationPane.add(massLabel, 1, 35, 4, 1);
		informationPane.add(massPlaceholder, 1, 36, 4, 1);
		informationPane.add(temperatureLabel, 1, 41, 4, 1);
		informationPane.add(temperaturePlaceholder, 1, 42, 4, 1);
		informationPane.add(radiusLabel, 1, 47, 4, 1);
		informationPane.add(radiusPlaceholder, 1, 48, 4, 1);
		informationPane.add(destroyPlanet, 1, 53, 4, 1);
		
		buttons.put("Destroy Planet", destroyPlanet);
		
		labels.put("Name", nameLabel);
		labels.put("Type", typeLabel);
		labels.put("Distance", distanceFromSunLabel);
		labels.put("Speed", speedLabel);
		labels.put("Mass", massLabel);
		labels.put("Temperature", temperatureLabel);
		labels.put("Radius", radiusLabel);
		labels.put("Name Placeholder", namePlaceholder);
		labels.put("Type Placeholder", typePlaceholder);
		labels.put("Distance Placeholder", distanceFromSunPlaceholder);
		labels.put("Speed Placeholder", speedPlaceholder);
		labels.put("Mass Placeholder", massPlaceholder);
		labels.put("Temperature Placeholder", temperaturePlaceholder);
		labels.put("Radius Placeholder", radiusPlaceholder);
	}
	private void setPlaceholderFont(Label...labels) {
		for (Label label : labels) {
	        label.setFont(placeholderFont);
	    }
		
	}
	private void setLabelFont(Label... labels) {
	    for (Label label : labels) {
	        label.setFont(labelFont);
	    }
	}
	private void setLabelFontColour(Label...labels) {
		for(Label label : labels) {
			label.setTextFill(Color.WHITE);
		}
	}
	
	@Override
	public GridPane getPane() {
		return informationPane;
	}

	@Override
	public Button getButton(String buttonName) {
		return buttons.get(buttonName);
	}

	@Override
	public Label getLabel(String labelName) {
		return labels.get(labelName);
	}

	@Override
	public double getHeight() {
		return 0;
	}
	
	public Image getPlanetImage() {
		return planetImage;
	}
	public void setPlanetImage(Image planetImage) {
		iv.setImage(planetImage);
	}
	public ImageView getIV() {
		return iv;
	}
	public void setCurrentlyViewedObject(GameObject object) {
		this.currentlyViewedObject = object;
	}
	
	public GameObject getCurrentlyViewedObject() {
		return currentlyViewedObject;
	}
	@Override
	public Slider getSlider(String sliderName) {
		return null;
	}
	

}

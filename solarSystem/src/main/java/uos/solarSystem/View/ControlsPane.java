package uos.solarSystem.View;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Pane with all buttons and controls used within the simulation.
 * Separated from the view class for better readability
 */

public class ControlsPane  implements PaneInterface{

	GridPane controlsPane;
	
	//HashMap used to avoid creation of unique getters for each button
	private HashMap<String, Button> buttons;
	private HashMap<String, Label> labels;
	private HashMap<String, Slider> sliders;
	private Button createPlanet;
	private Label testLabel;
	private Slider width, height, translateY, translateX, size, mass;
	private Image displayedObject;
	private ImageView iv;
	private TextField nameField;
	public ControlsPane() {
		buttons = new HashMap<>();
		labels = new HashMap<>();
		sliders = new HashMap<>();
		controlsPane = new GridPane();
		controlsPane.setHgap(7);
		controlsPane.setVgap(7);
		
		displayedObject = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/icePlanet (1).gif"));
		iv = new ImageView(displayedObject);
		iv.setFitHeight(50);
		iv.setFitWidth(50);
		
		nameField = new TextField("Object's Name");
		createPlanet = new Button("Create Object");
		
		testLabel = new Label("Press \"Create Object\"");
		width = new Slider(0.1,500,250);
		height = new Slider(0.1,300,150);
		translateY = new Slider(-100,100,0);
		translateX = new Slider(-100,100,0);
		size = new Slider(1,80,20);
		mass = new Slider(1,1000,0);
		
		testLabel.setFont(new Font("Arial Bold", 14));
		testLabel.setTextFill(Color.CYAN);
		
		
		// enable the marks on sliders
        width.setShowTickMarks(true);
        height.setShowTickMarks(true);
        translateY.setShowTickMarks(true);
        translateX.setShowTickMarks(true);
        size.setShowTickMarks(true);
        mass.setShowTickMarks(true);

        // enable the Labels on sliders
        width.setShowTickLabels(true);
        height.setShowTickLabels(true);
        translateY.setShowTickLabels(true);
        translateX.setShowTickLabels(true);
        size.setShowTickLabels(true);
        mass.setShowTickLabels(true);


        // set Major tick unit
        height.setMajorTickUnit(10);
        width.setMajorTickUnit(10);
        translateY.setMajorTickUnit(5);
        translateX.setMajorTickUnit(5);
        size.setMajorTickUnit(5);
        mass.setMajorTickUnit(10);
        
		
		controlsPane.add(height, 1, 2);
		controlsPane.add(width, 2, 2);
		controlsPane.add(translateY, 1, 3);
		controlsPane.add(translateX, 2,3);
		controlsPane.add(createPlanet, 1, 1);
		controlsPane.add(testLabel, 3, 1);
		controlsPane.add(mass, 3, 2);
		controlsPane.add(size, 3 ,3);
		controlsPane.add(iv, 4, 0, 3,4);
		controlsPane.add(nameField, 2,1);
//		controlsPane.setGridLinesVisible(true);

		
		buttons.put("CreatePlanet", createPlanet);
		labels.put("TestLabel", testLabel);
		sliders.put("Width", width);
		sliders.put("Height", height);
		sliders.put("TranslateY", translateY);
		sliders.put("TranslateX", translateX);
		sliders.put("Size", size);
		sliders.put("Mass", mass);
		
		

	}
	
	@Override
	public Button getButton(String buttonName) {
		return buttons.get(buttonName) ; 
	}
	@Override
	public Label getLabel(String labelName) {
		return labels.get(labelName);
	}
	@Override
	public Slider getSlider(String sliderName) {
		return sliders.get(sliderName);
	}
	@Override
	public double getHeight() {
		return controlsPane.getHeight();
	}

	@Override
	public GridPane getPane() {
		return controlsPane;
	}
	
	public void setDisplayedObject(Image image) {
		iv.setImage(image);
	}
	
	public ImageView getImageView() {
		return iv;
	}
	public Image getDisplayedObject() {
		return displayedObject;
	}
	public TextField getNameField() {
		return nameField;
	}
	
	

}

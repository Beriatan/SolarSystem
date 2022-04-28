package uos.solarSystem.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public interface PaneInterface {
	public GridPane getPane();
	public Button getButton(String buttonName);
	public Label getLabel(String labelName);
	public Slider getSlider(String sliderName);
	public double getHeight();
	
}

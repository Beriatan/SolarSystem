package uos.solarSystem.Model;

import javafx.scene.image.Image;

public class Planet extends GameObject {
	
	protected Image image;

	public Planet(double orbitHeight, double orbitWidth, double x, double y, String name, double radius, double mass) {
		super(orbitHeight, orbitWidth, x, y, name, radius, mass);
		type = "planet";
	}
	
	

}

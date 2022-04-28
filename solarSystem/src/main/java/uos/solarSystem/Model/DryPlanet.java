package uos.solarSystem.Model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class DryPlanet extends Planet implements GameObjectIF{

	public DryPlanet(double orbitHeight, double orbitWidth, double x, double y, String name, double radius, double mass) {
		super(orbitHeight, orbitWidth, x, y, name, radius, mass);
		setObjectTexture();
		type = "Dry Planet";
	}
	
	@Override
	public Image getImage() {
		return image;
	}
	/**
	 * Generates graphics for the planet
	 * Selects a number from 0 to 4 and picks the texture based on the result
	 */
	@Override
	public void setObjectTexture() {
		Random rand = new Random();
		int starType = rand.nextInt(4);
		switch (starType) {
		case 0: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (1).gif"));
			break;
		case 1: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (2).gif"));
			break;
		case 2: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (3).gif"));
			break;
		case 3: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (4).gif"));
			break;
		case 4: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/dryPlanet (5).gif"));
			break;
		}
		body.setFill(new ImagePattern(image));		
	}
}

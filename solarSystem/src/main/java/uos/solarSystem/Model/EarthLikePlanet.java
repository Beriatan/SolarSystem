package uos.solarSystem.Model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class EarthLikePlanet extends Planet implements GameObjectIF{

	public EarthLikePlanet(double orbitHeight, double orbitWidth, double x, double y, String name, double radius,
			double mass) {
		super(orbitHeight, orbitWidth, x, y, name, radius, mass);
		type = "Earth-like planet";
		setObjectTexture();
	}
	
	/**
	 * Returns the planet's texture image
	 */
	@Override
	public Image getImage() {
		return image;
	}

	/**
	 * Generates graphics for the planet
	 * Selects a number from 0 to 9 and picks the starlight and texture based on the result
	 */
	@Override
	public void setObjectTexture() {
		
		Random rand = new Random();
		int starType = rand.nextInt(9);
		switch (starType) {
		case 0: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (1).gif"));
			break;
		case 1: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (2).gif"));
			break;
		case 2: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (3).gif"));
			break;
		case 3: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (4).gif"));
			break;
		case 4: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (5).gif"));
			break;
		case 5: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (6).gif"));
			break;
		case 6: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (7).gif"));
			break;
		case 7: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (8).gif"));
			break;
		case 8: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (9).gif"));
			break;
		case 9: 
			image =new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/RockyPlanets/earthLikePlanet (10).gif"));
			break;
		}
		body.setFill(new ImagePattern(image));
	}

}

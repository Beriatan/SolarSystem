package uos.solarSystem.Model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class GasGiantWithRings extends Planet implements GameObjectIF{

	public GasGiantWithRings(double orbitHeight, double orbitWidth, double x, double y, String name, double radius,
			double mass) {
		super(orbitHeight, orbitWidth, x, y, name, radius, mass);
		setObjectTexture();
	}
	
	@Override
	public Image getImage() {
		return image;
	}
	/**
	 * Generates graphics for the planet
	 * Selects a number from 0 to 4 and picks the starlight and texture based on the result
	 */
	@Override
	public void setObjectTexture() {
		Random rand = new Random();
		int planetType = rand.nextInt(4);
		switch (planetType) {
		case 0: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (1).gif"));
			break;
		case 1: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (2).gif"));
			break;
		case 2: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (3).gif"));
			break;
		case 3: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (4).gif"));
			break;
		case 4: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiantRing (5).gif"));
			break;
		}
		body.setFill(new ImagePattern(image));
	}

}

package uos.solarSystem.Model;

import java.util.Random;

import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Star extends GameObject implements GameObjectIF{

	public Star(double orbitHeight, double orbitWidth, double x, double y, String name,  double radius, double mass ) {
		super( orbitHeight,  orbitWidth,  x,  y, name, radius, mass);
		type = "star";
		setObjectTexture();
	}
	

	@Override
	public Image getImage() {
		return image;			
	}
	/**
	 * Generates graphics for the star
	 * Selects a number from 0 to 6 and picks the starlight and texture based on the result
	 */
		@Override
	public void setObjectTexture() {
			Random rand = new Random();
			int starType = rand.nextInt(6);
			switch (starType) {
			case 0: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (1).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.WHITE));
				break;
			case 1: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (2).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.GREEN));
				break;
			case 2: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (3).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.YELLOW));
				break;
			case 3: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (4).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.PINK));
				break;
			case 4: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (5).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.PURPLE));
				break;
			case 5: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (6).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.BLUE));
				break;
			case 6: 
				image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/star (7).gif"));
				body.setEffect(new DropShadow(+25d, +2d, +2d, Color.RED));
				break;
			}
			body.setFill(new ImagePattern(image));
		}
		
		
}



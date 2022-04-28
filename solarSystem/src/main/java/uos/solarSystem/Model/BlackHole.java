package uos.solarSystem.Model;

import java.util.Random;

import javafx.animation.Timeline;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class BlackHole extends GameObject implements GameObjectIF{

	Timeline timeline;
	public BlackHole(String name, double radius,double mass) {
		super(0.1,0.1, 0, 0, name, radius, mass);
		type = "Black hole";
		setObjectTexture();
	}
	
	@Override
	public Image getImage() {
		return image;
	}


	/**
	 * Generates graphics for the black hole
	 * Selects a number from 0 to 7 and picks the black hole's and texture and glow based on the result
	 */
	@Override
	public void setObjectTexture() {
		// TODO Auto-generated method stub
		Random rand = new Random();
		int starType = rand.nextInt(7);
		switch (starType) {
		case 0: 
			
			
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (1).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 1: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (2).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 2: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (3).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 3: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (4).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 4: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (5).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 5: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (6).gif"));
			body.setEffect(new DropShadow(+25d, +3d, +3d, Color.BLACK));
			break;
		case 6: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (7).gif"));
			body.setEffect(new DropShadow(+25d, +2d, +2d, Color.BLACK));
			break;
		case 7: 
			image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/LargeObjects/blackHole (8).gif"));
			body.setEffect(new DropShadow(+25d, +2d, +2d, Color.BLACK));
			break;
		}
		body.setFill(new ImagePattern(image));
	}

}

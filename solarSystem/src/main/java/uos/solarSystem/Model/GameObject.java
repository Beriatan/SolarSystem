package uos.solarSystem.Model;


import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
//import static uos.solarSystem.Model.Constants.G;


public class GameObject {
	protected String name;
	protected Circle body;
	protected Ellipse orbit;
	protected PathTransition objectTransitionPath;
	protected Translate coordinates;
	double distancePerPixel  = 1E9;
	Timeline timeline;
	String type;
	Image image;
	
	double mass;
	double solarMass = 1.989E7;

	public GameObject( double orbitHeight, double orbitWidth, double x,double y, String name, double radius,  double mass)
	{
		this.name = name;
		this.mass = mass;
		type = "GameObject";
		image = new Image(this.getClass().getResourceAsStream("/uos/solarSystem/View/Graphics/GasGiants/gasGiant (1).gif"));
		body = new Circle(radius);
		orbit = new Ellipse();
		orbit.setStrokeWidth(0.5);
		orbit.setStroke(Color.YELLOW);
		orbit.setFill(Color.TRANSPARENT);
		orbit.setRadiusY(orbitHeight);
		orbit.setRadiusX(orbitWidth);
		
		orbit.setMouseTransparent(true);
		coordinates = new Translate();
		coordinates.setX(x);
		coordinates.setY(y);
		orbit.getTransforms().add(coordinates);
		objectTransitionPath = new PathTransition();
		//Set the transition path equal to the object's orbit
		objectTransitionPath.setPath(orbit);
		//Place the body on the orbit
		objectTransitionPath.setNode(body);
		//Ensure that the transition on the orbit is linear - the change of speed is managed by Newton's theorem of gravity
		objectTransitionPath.setInterpolator(Interpolator.LINEAR);
		//Rotate the planet whilst travelling on the orbit
		objectTransitionPath.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		//Continuous animation
		objectTransitionPath.setCycleCount(Timeline.INDEFINITE);
		objectTransitionPath.play();


		//Change the speed of the celestial body depending on its distance away from the star. The closer it is, the faster it moves.
		//Timeline is used to check the distance every 10 milliseconds and change the speed depending on the distance.
		//For the distance, the Pythagorean theorem is used:  distance^2 = x^2 + y^2, therefore distance = square root of x^2 plus y^2
		timeline = new Timeline();
        timeline.getKeyFrames().setAll(new KeyFrame(Duration.millis(10), e -> {
        	objectTransitionPath.rateProperty().setValue(getSpeed());
        }));
        
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
	}
	public Circle getObjectBody()
	{
		return body;
	}
	public double getTranslateX() {
		return body.getTranslateX();
	}
	public double getTranslateY() {
		return body.getTranslateY();
	}
	public double getRadius() {
		return body.getRadius();
	}
	public Ellipse getOrbitalPath() {
		return orbit;
	}
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	protected void setOrbitCoordinates(double x, double y) {
		coordinates.setX(x);
		coordinates.setY(y);

	}
	
	//Calculates the distance from the centre of the sun (0,0) using Pythagorean theorem
	//Where distance = square root of X and Y distance
	public double getDistanceFromSun() {
		return Math.sqrt(Math.pow(body.getTranslateX(),2) + Math.pow(body.getTranslateY(),2));
	}
	
	//Using Newtonian law of universal gravitation: where force of Gravity = constant G * (mass of Sun * mass of planet)/distance ^2
	//Then, to change force of gravity to speed, square the result
	//Constant G equal 6.67430E-9
	public double getSpeed() {
		return Math.sqrt(6.67430E-9*(solarMass*mass)/Math.pow(getDistanceFromSun(), 2));
	}
	
	public String getMassExponent() {
		if(mass<1E1) return "x 10^24";
		if(mass >=1E1 && mass < 1E2) return "x 10^25";
		if(mass >=1E2 && mass < 1E3) return "x 10^26";
		if(mass >=1E3 && mass < 1E4) return "x 10^27";
		if(mass >=1E4 && mass < 1E5) return "x 10^28";
		if(mass >=1E6 && mass < 1E7) return "x 10^29";
		if(mass >=1E7 && mass < 1E8) return "x 10^30";

		else return "";
	}
	public double getMass() {
		return mass;
	}
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
		body.setFill(new ImagePattern(image));
	}
	
	public double getTemperature() {
		double d = getDistanceFromSun();
		double temperature = 0;
			if(d > 744) 	temperature =  d* (-0.147845488);
			if(d <= 744 && d > 211)	 temperature = d*(-0.307910943);
			if(d <= 211 && d > 50) 	temperature = d*0.09628055;
			if(d <= 50  && d > 1)	temperature = d*5.1696623465;
			if(d <= 1 && d > 0) temperature = d*55050;
		
		return temperature;
	}
	
	
	
	
	
	
}


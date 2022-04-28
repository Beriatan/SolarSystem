package uos.solarSystem.Model;

public class Factory implements FactoryInterface {

	public Factory() {
		super();
	}

	@Override
	public GameObject createObject(String discrim, double orbitHeight, double orbitWidth, double x, double y,
			String name, double radius, double mass) {
					if(discrim.equals("star")) {
						return new Star(orbitHeight, orbitWidth, x, y,name,  radius,  mass);
					}
					if(discrim.equals("black hole")) {
						return new BlackHole(name,  radius,  mass);
					}
					if(discrim.equals("dry planet")) {
						return new DryPlanet(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
					if(discrim.equals("earth like planet")) {
						return new EarthLikePlanet(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
					if(discrim.equals("ice planet")) {
						return new IcePlanet(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
					if(discrim.equals("lava planet")) {
						return new LavaPlanet(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
					if(discrim.equals("gas giant")) {
						return new GasGiant(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
					if(discrim.equals("gas giant with rings")) {
						return new GasGiantWithRings(  orbitHeight,  orbitWidth,  x,  y, name,  radius,  mass);
					}
		return null;
	}

}

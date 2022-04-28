package uos.solarSystem.Model;

public interface FactoryInterface {
				GameObject createObject(String discrim, 
																double orbitHeight,
																double orbitWidth,
																double x,
																double y, 
																String name, 
																double radius,  
																double mass);
}

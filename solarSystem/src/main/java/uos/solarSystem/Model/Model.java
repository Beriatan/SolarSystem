package uos.solarSystem.Model;
import java.util.ArrayList;

public class Model {
	
	ArrayList<GameObject> objects;
	Factory factory;
	
	public Model() {
		super();
		factory = new Factory();
		objects = new ArrayList<>();
		
		//create Earth and Sun
		addObject(factory.createObject("star", 0.1, 0.1, 0, 0, "Sun", 90, 19890000));
		addObject(factory.createObject("earth like planet", 150, 150, 0, 0,"Earth", 15, 5.972));

	}
	/**	
	 * Adds the celestial object into the simulation
	 * @param object
	 */
	public void addObject(GameObject object) {
		objects.add(object);
	}
	
	/*
	 * Returns the list of all objects
	 */
	public ArrayList<GameObject> getObjects() {
		return objects;
	}
	
	public GameObject getObject(int index) {
		return objects.get(index);
	}
	
	/*
	 * Removes given object from the list
	 */
	public void removeObject(GameObject object) {
		objects.remove(object);
	}
	
	/*
	 * Removes all objects from the list
	 */
	public void removeAllObjects() {
		if(objects.size() > 0) {
			objects.clear();
		}else System.out.println("The list was already empty");
	}

}

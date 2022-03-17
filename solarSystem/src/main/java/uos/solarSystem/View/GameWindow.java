package uos.solarSystem.View;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameWindow {
	
	Pane root;
	Scene scene;
	
	public GameWindow() {
		root = new Pane();
		scene = new Scene(root, 1024,768);
	}

}

package uos.solarSystem.View;

import java.util.HashMap;

import javafx.scene.media.AudioClip;


public class SoundPlayer {
	private HashMap<String, AudioClip> sounds;
	AudioClip explosion, backgroundMusic, dryPlanet, blackHole, earthLike, gasGiant, gasGiantWithRings,icePlanet, lavaPlanet ;
	
	public SoundPlayer() {
		sounds = new HashMap<>();
	
		explosion = new AudioClip(ClassLoader.getSystemResource("/uos/solarSystem/View/Audio/zapsplat_explosion_designed_large_heavy_with_small_shockwave_002_66996.mp3").toExternalForm());
		sounds.put("Explosion", explosion);
	}
	
	public void playSound(String soundName, double volume) {
		sounds.get(soundName).play(volume);
	}

}

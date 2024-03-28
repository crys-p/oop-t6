package com.mygdx.engine.SoundManager;

import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SimulationManager.SimulationManager;

import java.util.HashMap;

public class SoundManager {
	HashMap<SceneManager.SceneType, BackgroundMusic> backgroundMusicMap;
	HashMap<SoundEffectType, SoundEffect> soundEffectMap;
	private boolean musicOn = true; // Initially music is on
	private SimulationManager simulationManager;
	public SoundManager() {
		backgroundMusicMap = new HashMap<>();
		soundEffectMap = new HashMap<>();


		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("SoundManager initialized"); // Log initialization message
	}

	public void createSounds() {
		// Create Background Music
		backgroundMusicMap.put(SceneManager.SceneType.START, new BackgroundMusic("start_music(new).mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.GAMEL1, new BackgroundMusic("game_music(new).mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.GAMEL2, new BackgroundMusic("game_music(new).mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.GAMEL3, new BackgroundMusic("game_music(new).mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.MENU, new BackgroundMusic("start_music(new).mp3")); //same as start music
		backgroundMusicMap.put(SceneManager.SceneType.VICTORY, new BackgroundMusic("win_music(new).mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.LOSE, new BackgroundMusic("lose_music(new).mp3"));

		// Create Sound Effects
		soundEffectMap.put(SoundEffectType.COLLECT, new SoundEffect("collect_sfx.mp3"));
		soundEffectMap.put(SoundEffectType.HIT, new SoundEffect("hit_sfx.mp3"));
	}

	public void playMusic(SceneManager.SceneType scene) {
		if (musicOn) {
			backgroundMusicMap.get(scene).play();
		}
	}

	public void playSoundEffect(SoundEffectType sfx) {
		{
			if (musicOn) {
				soundEffectMap.get(sfx).play();
			}
		}
	}

	public void stopAllMusic() {
		for (BackgroundMusic music : backgroundMusicMap.values()) {
			music.stop();
		}
	}

	public void toggleMusic(SceneManager.SceneType currentScene) {
		musicOn = !musicOn;
		if (!musicOn) {
			stopAllMusic();
			System.out.println("Music turned off");
		} else {
			backgroundMusicMap.get(currentScene).play();
		}
	}


	public void dispose() {
		for (BackgroundMusic music : backgroundMusicMap.values()) {
			music.dispose();
		}
	}
}

package com.mygdx.game.SoundManager;

import com.mygdx.game.SceneManager.SceneManager;

import java.util.HashMap;

public class SoundManager {
	HashMap<SceneManager.SceneType, BackgroundMusic> backgroundMusicMap;
	HashMap<SoundEffectType, SoundEffect> soundEffectMap;
	private boolean musicOn = true; // Initially music is on

	public SoundManager() {
		backgroundMusicMap = new HashMap<>();
		soundEffectMap = new HashMap<>();
	}

	public void createSounds() {
		// Create Background Music
		backgroundMusicMap.put(SceneManager.SceneType.START, new BackgroundMusic("background_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.GAME, new BackgroundMusic("background_music_3.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.MENU, new BackgroundMusic("menu_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.VICTORY, new BackgroundMusic("win_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.LOSE, new BackgroundMusic("lose_music.mp3"));

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

			switch (currentScene) {
				case GAME:
					backgroundMusicMap.get(SceneManager.SceneType.GAME).play();
					break;
				case START:
					backgroundMusicMap.get(SceneManager.SceneType.START).play();
					break;
				case MENU:
					backgroundMusicMap.get(SceneManager.SceneType.MENU).play();
					break;
				case VICTORY:
					backgroundMusicMap.get(SceneManager.SceneType.VICTORY).play();
					break;
				case LOSE:
					backgroundMusicMap.get(SceneManager.SceneType.LOSE).play();
					break;
				default:
					System.out.println("No music for the current scene.");
					break;
			}
		}
	}


	public void dispose() {
		for (BackgroundMusic music : backgroundMusicMap.values()) {
			music.dispose();
		}
	}
}

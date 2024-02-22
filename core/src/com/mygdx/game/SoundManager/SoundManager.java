package com.mygdx.game.SoundManager;

import com.mygdx.game.SceneManager.SceneManager;

import java.util.HashMap;

public class SoundManager {
	HashMap<SceneManager.SceneType, BackgroundMusic> backgroundMusicMap;
	private SoundEffect soundEffectGain;
	private SoundEffect soundEffectHit;

	private boolean musicOn = true; // Initially music is on

//	public SoundManager(String startSceneMusicFile, String gameSceneMusicFile,String menuSceneMusicFile, String soundEffectFile) {
//		startSceneMusic = new BackgroundMusic(startSceneMusicFile);
//		gameSceneMusic = new BackgroundMusic(gameSceneMusicFile);
//		menuSceneMusic = new BackgroundMusic(menuSceneMusicFile);
//		soundEffect = new SoundEffect(soundEffectFile);
//	}

	public SoundManager() {
		backgroundMusicMap = new HashMap<>();
	}

	public void createSounds() {
		// Create Background Music
		backgroundMusicMap.put(SceneManager.SceneType.START, new BackgroundMusic("background_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.GAME, new BackgroundMusic("background_music_3.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.MENU, new BackgroundMusic("menu_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.VICTORY, new BackgroundMusic("win_music.mp3"));
		backgroundMusicMap.put(SceneManager.SceneType.LOSE, new BackgroundMusic("lose_music.mp3"));


		// Create Sound Effects
		this.soundEffectGain = new SoundEffect("collect_sfx.mp3");
		this.soundEffectHit = new SoundEffect("hit_sfx.mp3");
	}

	public void playMusic(SceneManager.SceneType scene) {
		if (musicOn) {
			switch (scene) {
				case START:
					backgroundMusicMap.get(SceneManager.SceneType.START).play();
					break;
				case GAME:
					backgroundMusicMap.get(SceneManager.SceneType.GAME).play();
					break;
				case MENU:
					backgroundMusicMap.get(SceneManager.SceneType.MENU).play();
					break;
				case VICTORY:
					backgroundMusicMap.get(SceneManager.SceneType.VICTORY).play();
					break;
				case LOSE:
					backgroundMusicMap.get(SceneManager.SceneType.LOSE).play();
			}
		}
	}

//	public void playStartSceneMusic() {
//		if (musicOn) {
//			startSceneMusic.play();
//			System.out.println("Start scene music played");
//		}
//	}
//
//	public void playGameSceneMusic() {
//		if (musicOn) {
//			gameSceneMusic.play();
//			System.out.println("Game scene music played");
//		}
//	}
//
//	public void playMenuSceneMusic() {
//		if (musicOn) {
//			menuSceneMusic.play();
//			System.out.println("Game scene music played");
//		}
//	}

	public void stopAllMusic() {
		for (BackgroundMusic music : backgroundMusicMap.values()) {
			music.stop();
		}
//		startSceneMusic.stop();
//		gameSceneMusic.stop();
//		menuSceneMusic.stop();
		System.out.println("All music stopped");
	}

	public void toggleMusic() {
		musicOn = !musicOn;
		if (!musicOn) {
			stopAllMusic();
			System.out.println("Music turned off");
		} else {
//			playGameSceneMusic(); // Or any other appropriate method to play music
			backgroundMusicMap.get(SceneManager.SceneType.GAME).play();
			System.out.println("Music turned on");
		}
	}

	public void playSoundEffect(int num) {
		if (num == 1) {
			soundEffectGain.play();
		} else if (num == 2) {
			soundEffectHit.play();
		}
	}

	public void dispose() {
		for (BackgroundMusic music : backgroundMusicMap.values()) {
			music.dispose();
		}
	}
}

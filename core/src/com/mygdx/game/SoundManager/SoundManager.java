package com.mygdx.game.SoundManager;

public class SoundManager {
	private BackgroundMusic startSceneMusic;
	private BackgroundMusic gameSceneMusic;
	private SoundEffect soundEffect;
	private boolean musicOn = true; // Initially music is on

	public SoundManager(String startSceneMusicFile, String gameSceneMusicFile, String soundEffectFile) {
		startSceneMusic = new BackgroundMusic(startSceneMusicFile);
		gameSceneMusic = new BackgroundMusic(gameSceneMusicFile);
		soundEffect = new SoundEffect(soundEffectFile);
	}

	public void playStartSceneMusic() {
		if (musicOn) {
			startSceneMusic.play();
			System.out.println("Start scene music played");
		}
	}

	public void playGameSceneMusic() {
		if (musicOn) {
			gameSceneMusic.play();
			System.out.println("Game scene music played");
		}
	}

	public void stopAllMusic() {
		startSceneMusic.stop();
		gameSceneMusic.stop();
		System.out.println("All music stopped");
	}

	public void toggleMusic() {
		musicOn = !musicOn;
		if (!musicOn) {
			stopAllMusic();
			System.out.println("Music turned off");
		} else {
			playGameSceneMusic(); // Or any other appropriate method to play music
			System.out.println("Music turned on");
		}
	}

	public void playSoundEffect() {
		soundEffect.play();
	}

	public void dispose() {
		startSceneMusic.dispose();
		gameSceneMusic.dispose();
		soundEffect.dispose();
	}
}

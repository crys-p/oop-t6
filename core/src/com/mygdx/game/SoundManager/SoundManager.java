package com.mygdx.game.SoundManager;

public class SoundManager {
	private BackgroundMusic startSceneMusic;
	private BackgroundMusic gameSceneMusic;
	private SoundEffect soundEffect;

	public SoundManager(String startSceneMusicFile, String gameSceneMusicFile, String soundEffectFile) {
		startSceneMusic = new BackgroundMusic(startSceneMusicFile);
		gameSceneMusic = new BackgroundMusic(gameSceneMusicFile);
		soundEffect = new SoundEffect(soundEffectFile);
	}

	public void playStartSceneMusic() {
		startSceneMusic.play();
	}

	public void playGameSceneMusic() {
		gameSceneMusic.play();
	}

	public void stopAllMusic() {
		startSceneMusic.stop();
		gameSceneMusic.stop();
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

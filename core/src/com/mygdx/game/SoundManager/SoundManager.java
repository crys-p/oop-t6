package com.mygdx.game.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.Random;

public class SoundManager {
	private Music startSceneMusic;
	private Music gameSceneMusic;
	private Sound soundEffect;

	public SoundManager(String startSceneMusicFile, String gameSceneMusicFile, String soundEffectFile) {
		// Load the start scene background music
		startSceneMusic = Gdx.audio.newMusic(Gdx.files.internal(startSceneMusicFile));
		// Load the game scene background music
		gameSceneMusic = Gdx.audio.newMusic(Gdx.files.internal(gameSceneMusicFile));

		// Load the sound effect
		soundEffect = Gdx.audio.newSound(Gdx.files.internal(soundEffectFile));
	}

	public void playStartSceneMusic() {
		// Start playing the start scene background music (looping)
		startSceneMusic.setLooping(true);
		startSceneMusic.play();
	}

	public void playGameSceneMusic() {
		// Start playing the game scene background music (looping)
		gameSceneMusic.setLooping(true);
		gameSceneMusic.play();
	}

	public void stopAllMusic() {
		// Stop playing all background music
		startSceneMusic.stop();
		gameSceneMusic.stop();
	}

	public void playSoundEffect() {
		// Play the sound effect
		soundEffect.play();
	}

	public void dispose() {
		// Dispose of all audio resources when they're no longer needed
		startSceneMusic.dispose();
		gameSceneMusic.dispose();
		soundEffect.dispose();
	}
}

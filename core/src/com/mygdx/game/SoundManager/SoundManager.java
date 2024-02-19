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
		// to load the start scene background music
		startSceneMusic = Gdx.audio.newMusic(Gdx.files.internal(startSceneMusicFile));
		// to load the game scene background music
		gameSceneMusic = Gdx.audio.newMusic(Gdx.files.internal(gameSceneMusicFile));

		// to load the sound effect
		soundEffect = Gdx.audio.newSound(Gdx.files.internal(soundEffectFile));
	}

	public void playStartSceneMusic() {
		// start playing the start scene background music (looping)
		startSceneMusic.setLooping(true);
		startSceneMusic.play();
	}

	public void playGameSceneMusic() {
		// start playing the game scene background music (looping)
		gameSceneMusic.setLooping(true);
		gameSceneMusic.play();
	}

	public void stopAllMusic() {
		// stop playing all background music, can maybe split into stop Start and Game if i want
		startSceneMusic.stop();
		gameSceneMusic.stop();
	}

	public void playSoundEffect() {
		// play the sound effect
		soundEffect.play();
	}

	public void dispose() {
		// dispose of all audio resources when they're no longer needed
		// not use at all. hmmm dk
		startSceneMusic.dispose();
		gameSceneMusic.dispose();
		soundEffect.dispose();
	}
}

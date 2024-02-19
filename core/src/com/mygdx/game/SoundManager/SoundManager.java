package com.mygdx.game.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundManager {
	private Music backgroundMusic;
	private Sound soundEffect;

	public SoundManager(String backgroundMusicFile, String soundEffectFile) {
		// Load the background music
		backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal(backgroundMusicFile));
		// Load the sound effect
		soundEffect = Gdx.audio.newSound(Gdx.files.internal(soundEffectFile));

		//this.backgroundMusic = new BackgroundMusic(backgroundMusicFile);
		//this.soundEffect = new SoundEffect(soundEffectFile);
	}

	public void playBackgroundMusic() {
		// Start playing the background music (looping)
		backgroundMusic.setLooping(true);
		backgroundMusic.play();
	}

	public void stopBackgroundMusic() {
		// Stop playing the background music
		backgroundMusic.stop();
	}

	public void playSoundEffect() {
		// Play the sound effect
		soundEffect.play();
	}

	public void dispose() {
		// Dispose of the audio resources when they're no longer needed
		backgroundMusic.dispose();
		soundEffect.dispose();
	}
}
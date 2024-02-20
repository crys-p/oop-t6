package com.mygdx.game.SoundManager;

public abstract class Sound {
	protected String filename;

	public Sound(String filename) {
		this.filename = filename;
	}

	// sound file have no usage as soundeffect and BackgroundMusic composition SoundManager
	// can be remove. leave it here for now
	public abstract void playSound();
	public abstract void stop();
	public abstract void dispose();
}

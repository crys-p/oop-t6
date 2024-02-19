package com.mygdx.game.SoundManager;

public abstract class 	Sound {
	private String filename;

	public Sound(String filename) {
		this.filename = filename;
	}

	public abstract void play();

	public abstract void stop();
}
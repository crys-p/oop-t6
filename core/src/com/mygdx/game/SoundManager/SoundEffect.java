package com.mygdx.game.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
	private Sound sound;

	public SoundEffect(String filename) {
		sound = Gdx.audio.newSound(Gdx.files.internal(filename));
	}

	public void play() {
		sound.play();
	}

	public void dispose() {
		sound.dispose();
	}
}

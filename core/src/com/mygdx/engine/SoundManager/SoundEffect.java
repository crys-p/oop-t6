package com.mygdx.engine.SoundManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class SoundEffect {
	private Sound sound;

	protected SoundEffect(String filename) {
		sound = Gdx.audio.newSound(Gdx.files.internal(filename));
	}

	protected void play() {
		sound.play();
	}

	protected void dispose() {
		sound.dispose();
	}
}

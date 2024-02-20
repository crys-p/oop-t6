package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import java.util.HashMap;
import java.util.Map;

public class Keyboard {
	protected Map<Integer, Boolean> keys;

	public Keyboard() {
		keys = new HashMap<>();
	}

	//should be called before updateInputs(in IOManager)
	public void updateKeys() {
		keys.clear();
		keys.put(Keys.UP, Gdx.input.isKeyPressed(Keys.UP));
		keys.put(Keys.DOWN, Gdx.input.isKeyPressed(Keys.DOWN));
		keys.put(Keys.LEFT, Gdx.input.isKeyPressed(Keys.LEFT));
		keys.put(Keys.RIGHT, Gdx.input.isKeyPressed(Keys.RIGHT));
	}

	public Map<Integer, Boolean> getKeys() {
		return keys;
	}
}

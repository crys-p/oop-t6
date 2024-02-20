package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Keyboard{
	protected Map<Integer, Boolean> keys;
	private Input input;

	protected Keyboard(Input input) {
        this.input = input;
	}

	protected void processInput() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			input.receiveInput(Keys.LEFT);
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			input.receiveInput(Keys.RIGHT);
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			input.receiveInput(Keys.UP);
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			input.receiveInput(Keys.DOWN);
		}
	}
/*	protected boolean isKeyPressed(int keyCode) {
		boolean keyPressed = Gdx.input.isKeyPressed(keyCode);

		if (keyPressed) {
			return true;
		} else {
			return false;
		}
	}*/

	//should be called before updateInputs(in IOManager)
/*	protected void updateKeys() {
		keys.clear();
		keys.put(Keys.UP, Gdx.input.isKeyPressed(Keys.UP));
		keys.put(Keys.DOWN, Gdx.input.isKeyPressed(Keys.DOWN));
		keys.put(Keys.LEFT, Gdx.input.isKeyPressed(Keys.LEFT));
		keys.put(Keys.RIGHT, Gdx.input.isKeyPressed(Keys.RIGHT));
	}*/

/*	protected List<Integer> getKeys() {
		return new ArrayList<>(keys.keySet());
	}*/

}

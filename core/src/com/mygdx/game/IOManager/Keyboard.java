package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

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

}

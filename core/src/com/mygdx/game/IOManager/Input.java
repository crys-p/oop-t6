package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import java.util.ArrayList;
import java.util.List;

public abstract class Input {
	protected Keyboard keyboard;
	protected Mouse mouse;
	
	public Input(Keyboard keyboard, Mouse mouse) {
		this.keyboard = keyboard;
		this.mouse = mouse;
	}

	public Keyboard getKeyboard() {
		return keyboard;
	}

	public void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

	public Mouse getMouse() {
		return mouse;
	}

	public void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	public abstract void update();

	//retrieve keys as list
	public List<Integer> getKeys() {
		List<Integer> pressedKeys = new ArrayList<>();
		if (Gdx.input.isKeyPressed(Keys.UP))
			pressedKeys.add(Keys.UP);
		if (Gdx.input.isKeyPressed(Keys.DOWN))
			pressedKeys.add(Keys.DOWN);
		if (Gdx.input.isKeyPressed(Keys.LEFT))
			pressedKeys.add(Keys.LEFT);
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
			pressedKeys.add(Keys.RIGHT);
		return pressedKeys;
	}
}
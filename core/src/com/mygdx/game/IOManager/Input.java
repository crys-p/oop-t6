package com.mygdx.game.IOManager;

import java.util.List;

class Input {
	protected Keyboard keyboard;
	protected Mouse mouse;
	private  IOManager ioManager;

	protected Input(IOManager ioManager) {
		this.ioManager = ioManager;
	}

	protected void receiveInput(int key) {
		// Process input if needed
		ioManager.updateMovement();
	}
	
	/*protected Input(Keyboard keyboard, Mouse mouse) {
		this.keyboard = keyboard;
		this.mouse = mouse;
	}

	protected Keyboard getKeyboard() {
		return keyboard;
	}

	protected void setKeyboard(Keyboard keyboard) {
		this.keyboard = keyboard;
	}

	protected Mouse getMouse() {
		return mouse;
	}

	protected void setMouse(Mouse mouse) {
		this.mouse = mouse;
	}

	protected void update() {
		mouse.update();
		System.out.println("mouse update");

		List<Integer> pressedKeys = getKeys();
		System.out.println("keys update");

		// Pass pressed keys to IOManager
		IOManager.getInstance().handleInput(pressedKeys);
	}

	//retrieve keys as list
<<<<<<< HEAD
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




	protected abstract List<Integer> getKeys();*/

}
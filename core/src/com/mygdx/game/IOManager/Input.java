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
	protected abstract List<Integer> getKeys();*/
}
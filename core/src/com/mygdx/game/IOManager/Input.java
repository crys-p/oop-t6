package com.mygdx.game.IOManager;

import java.util.List;

class Input {
	protected Keyboard keyboard;
	protected Mouse mouse;
	private  IOManager ioManager;

	protected Input(IOManager ioManager, Keyboard keyboard, Mouse mouse) {
		this.ioManager = ioManager;
		this.keyboard = keyboard;
		this.mouse = mouse;
	}

	protected void receiveInput(int key) {
		// Process input if needed
		ioManager.updateMovement();
	}

	protected void receiveMouseInput() {
		float mouseX = mouse.getMouseX();
		float mouseY = mouse.getMouseY();
		ioManager.updateMousePosition(mouseX, mouseY);
	}

}
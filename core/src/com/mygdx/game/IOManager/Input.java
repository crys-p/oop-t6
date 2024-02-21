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

	/*	protected void receiveMouseInput(int button) {
		ioManager.updateMouse();
	}*/

}
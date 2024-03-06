package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	protected Keyboard keyboard;
	protected Mouse mouse;

	protected Input () {
		this.keyboard = new Keyboard();
		this.mouse = new Mouse();
		Gdx.input.setInputProcessor(this); // Set Input as Input Processor
	}

	//Keyboard
	protected void setUpKeyStates() {
		for (int keycode : keyboard.keysAccepted) {
			keyboard.keyStates.put(keycode, false);
		}
	}

	// Set key states based on pressed keys
	public void handleKeyPress(int keycode, boolean isPressed) {
		keyboard.keyStates.put(keycode, isPressed);
	}
	@Override
	public boolean keyDown(int keycode) {
		if (keyboard.keysAccepted.contains(keycode)) {
			handleKeyPress(keycode, true);
		}
		return true; // To indicate event was handled
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keyboard.keysAccepted.contains(keycode)) {
			handleKeyPress(keycode, false);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	//Mouse
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		updateMousePosition(screenX, screenY);
		handleMouseButtonPress(button, true);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		updateMousePosition(screenX, screenY);
		handleMouseButtonPress(button, false);
		return true;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		updateMousePosition(screenX, screenY);
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		updateMousePosition(screenX, screenY);
		return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// Handle mouse scroll if needed
		return false;
	}

	protected void updateMousePosition(float screenX, float screenY) {
		mouse.getMouseX();
		mouse.getMouseY();

		mouse.mouseX = screenX;
		mouse.mouseY = Gdx.graphics.getHeight() - screenY; // Invert Y-axis
	}

	protected void handleMouseButtonPress(int button, boolean isPressed) {
		switch (button) {
			case com.badlogic.gdx.Input.Buttons.LEFT:
				mouse.leftButtonPressed = isPressed;
				break;
			case com.badlogic.gdx.Input.Buttons.RIGHT:
				mouse.rightButtonPressed = isPressed;
				break;
			// Add cases for other mouse buttons as needed
		}
	}
	protected void updateMouse() {
		int screenX = Gdx.input.getX();
		int screenY = Gdx.graphics.getHeight() - Gdx.input.getY(); // Invert Y-axis

		updateMousePosition(screenX, screenY);

		// Handle mouse button press
		if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.LEFT)) {
			handleMouseButtonPress(com.badlogic.gdx.Input.Buttons.LEFT, true);
		} else if (Gdx.input.isButtonPressed(com.badlogic.gdx.Input.Buttons.RIGHT)) {
			handleMouseButtonPress(com.badlogic.gdx.Input.Buttons.RIGHT, true);
		} else {

		}
	}
}
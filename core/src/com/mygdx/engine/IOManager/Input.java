package com.mygdx.engine.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Input implements InputProcessor {
	protected Keyboard keyboard;
	protected Mouse mouse;
	private IOManager ioManager;

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
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		updateMousePosition(screenX, screenY);
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

	//Mouse
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
		mouse.mouseX = screenX;
		mouse.mouseY = screenY;
	}

	// Method to detect which button was clicked based on touch coordinates
	// Method to detect which button was clicked based on touch coordinates
	protected int detectClickedButton(float touchX, float touchY) {
		int buttonIndex = -1;
		float buttonWidth = 200f; // Width of each button
		float buttonHeight = 50f; // Height of each button
		float buttonYDiff = 100f; // Difference in y-coordinate between buttons

		float buttonGameWidth = 200f; // Width of each button
		float buttonGameHeight = 100f; // Height of each button

		float loseGameWidth = 300f; // Adjust this value to match the width of your button
		float loseGameHeight = 100f; // Adjust this value to match the height of your button

		// Adjust the x and y coordinates based on the button index
		float buttonX = ioManager.SCREEN_WIDTH - buttonGameWidth - 20; // 20 is the padding from the right edge
		float buttonY = 20; // 20 is the padding from the top edge

		// Check if the touch coordinates are within the bounds of each button
		// Adjust the y-coordinate bounds based on the button index
		if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 300 + buttonIndex * buttonYDiff && touchY <= 300 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 0;
		} else if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 400 + buttonIndex * buttonYDiff && touchY <= 400 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 1;
		} else if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 500 + buttonIndex * buttonYDiff && touchY <= 500 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 2;
		} else if (touchX >= buttonX && touchX <= buttonX + buttonGameWidth && touchY >= buttonY && touchY <= buttonY + buttonGameHeight) {
			buttonIndex = 3;
		} else if (touchX >= buttonX && touchX <= buttonX + buttonGameWidth && touchY >= ioManager.SCREEN_HEIGHT - buttonGameHeight - 20 && touchY <= ioManager.SCREEN_HEIGHT - 20) {
			buttonIndex = 4;
		}  else if (touchX >= 500 && touchX <= 660 + loseGameWidth && touchY >= 660 + buttonIndex * buttonYDiff && touchY <= 660 + buttonIndex * buttonYDiff + loseGameHeight) {
			buttonIndex = 5;
		}

		return buttonIndex;
	}
}
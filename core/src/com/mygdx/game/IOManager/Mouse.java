package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;

public class Mouse implements InputProcessor{

	protected float mouseX;
	protected float mouseY;
	private Input input;
	private boolean leftButtonPressed;
	private boolean rightButtonPressed;

	protected Mouse(Input input) {
		this.input = input;
	}

	public float getMouseX() {
		return mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

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

	protected void updateMousePosition(int screenX, int screenY) {
		input.receiveMouseInput();
		mouseX = screenX;
		mouseY = Gdx.graphics.getHeight() - screenY; // Invert Y-axis
	}

	protected void handleMouseButtonPress(int button, boolean isPressed) {
		switch (button) {
			case com.badlogic.gdx.Input.Buttons.LEFT:
				leftButtonPressed = isPressed;
				break;
			case com.badlogic.gdx.Input.Buttons.RIGHT:
				rightButtonPressed = isPressed;
				break;
			// Add cases for other mouse buttons as needed
		}
	}

}

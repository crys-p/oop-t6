package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.ArrayList;
import java.util.List;

class Mouse {
	protected float lastX;
	protected float lastY;
	protected float deltaX;
	protected float deltaY;
	protected List<Integer> buttons;

	protected Mouse(Keyboard keyboard, Mouse mouse) {
        lastX = Gdx.input.getX();
		lastY = Gdx.input.getY();
		deltaX = 0;
		deltaY = 0;
		buttons = new ArrayList<>();
	}

	protected void update() {
		updateMouse();
	}

	private void updateMouse() {
		deltaX = Gdx.input.getX() - lastX;
		deltaY = Gdx.input.getY() - lastY;

		// Update last position for the next frame
		lastX = Gdx.input.getX();
		lastY = Gdx.input.getY();

		// Update pressed buttons
		buttons.clear();
		if (Gdx.input.isButtonPressed(Buttons.LEFT))
			buttons.add(Buttons.LEFT);
		if (Gdx.input.isButtonPressed(Buttons.RIGHT))
			buttons.add(Buttons.RIGHT);
		// Add more button checks if needed
	}

	//keep track of mouse's position changes using deltaX/Y
	protected float getDeltaX() {
		return deltaX;
	}

	protected float getDeltaY() {
		return deltaY;
	}

	protected List<Integer> getButtons() {
		return buttons;
	}

	protected List<Integer> getKeys() {
		// Override the method from Input if needed
		return new ArrayList<>();
	}
}

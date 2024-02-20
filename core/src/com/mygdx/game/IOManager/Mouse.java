package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import java.util.ArrayList;
import java.util.List;

public class Mouse {
	private float lastX;
	private float lastY;
	private float deltaX;
	private float deltaY;
	private List<Integer> buttons;

	public Mouse() {
		lastX = Gdx.input.getX();
		lastY = Gdx.input.getY();
		deltaX = 0;
		deltaY = 0;
		buttons = new ArrayList<>();
	}

	public void update() {
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

	//keep track of mouse's position changes suing deltaX/Y
	public float getDeltaX() {
		return deltaX;
	}

	public float getDeltaY() {
		return deltaY;
	}

	public List<Integer> getButtons() {
		return buttons;
	}
}

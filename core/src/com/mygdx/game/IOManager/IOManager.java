package com.mygdx.game.IOManager;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.EntityManager.EntityManager;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IOManager implements InputProcessor {
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	//Reference to EntityManager
	private EntityManager entityManager;
	private boolean leftKeyPressed = false;
	private boolean rightKeyPressed = false;
	private boolean upKeyPressed = false;
	private boolean downKeyPressed = false;
	public IOManager (EntityManager entityManager) {
		this.entityManager = entityManager;
		Gdx.input.setInputProcessor(this); // Set IOManager as Input Processor
	}

	@Override
	public boolean keyDown(int keycode) {
		handleKeyPress(keycode, true);
		return true; // To indicate event was handled
	}

	@Override
	public boolean keyUp(int keycode) {
		handleKeyPress(keycode, false);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}

/*	public void processInput(int key) {
		System.out.println("processing input: " + key);

		if (entityManager != null) {
			entityManager.inputMovement(Collections.singletonList(key));
		} else {
			System.out.println("EntityManager is null");
		}

	}*/

	public void handleKeyPress(int keycode, boolean isPressed) {
		switch (keycode) {
			case Input.Keys.LEFT:
				leftKeyPressed = isPressed;
				break;
			case Input.Keys.RIGHT:
				rightKeyPressed = isPressed;
				break;
			case Input.Keys.UP:
				upKeyPressed = isPressed;
				break;
			case Input.Keys.DOWN:
				downKeyPressed = isPressed;
				break;
		}
		updateMovement();
	}

	public void updateMovement() {
		List<Integer> keys = new ArrayList<>();
		if (leftKeyPressed) keys.add(Input.Keys.LEFT);
		if (rightKeyPressed) keys.add(Input.Keys.RIGHT);
		if (upKeyPressed) keys.add(Input.Keys.UP);
		if (downKeyPressed) keys.add(Input.Keys.DOWN);

		if (!keys.isEmpty()) {
			entityManager.inputMovement(keys);
		}
	}

	/*private static IOManager instance = new IOManager();

	private final List<Input> inputList;
	
	public IOManager() {
		inputList = new ArrayList<>();
	}

	public static IOManager getInstance() {
		return instance;
	}

	//Add input to list
	public void addInput(Input input) {
		inputList.add(input);
	}

	// Handle pressed keys and communicate with EntityManager
	public void handleInput(List<Integer> pressedKeys) {
		entityManager.inputMovement(pressedKeys);
		System.out.println("updated pressed");
	}

	//Update all input in the list and communicate with EntityManager
	public void updateInputs() {
		for (Input input : inputList) {
			input.update();

			List<Integer> keys = input.getKeys();
			if (entityManager != null) {
				entityManager.inputMovement(keys);
				System.out.println("updated");
			}

		}

	}*/


}
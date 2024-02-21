package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.mygdx.game.EntityManager.EntityManager;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SoundManager.SoundManager;

public class IOManager implements InputProcessor {
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;

	private Output output;
	private Input input;
	private SoundManager soundManager; // Reference to SoundManager
	private SceneManager sceneManager;

	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private EntityManager entityManager; //Reference to EntityManager
	private boolean leftKeyPressed = false;
	private boolean rightKeyPressed = false;
	private boolean upKeyPressed = false;
	private boolean downKeyPressed = false;
	private float mouseX;
	private float mouseY;
	private boolean leftButtonPressed;
	private boolean rightButtonPressed;

	public IOManager(EntityManager entityManager, int numButtons, SoundManager soundManager, SceneManager sceneManager) {

		this.entityManager = entityManager;
		this.soundManager = soundManager;
		this.sceneManager = sceneManager;
		Gdx.input.setInputProcessor(this); // Set IOManager as Input Processor
		output = new Output(numButtons);

	}

	// Keys
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

	// Mouse
	public boolean isLeftButtonPressed() {
		return leftButtonPressed;
	}

	public boolean isRightButtonPressed() {
		return rightButtonPressed;
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

	public void updateMousePosition(float mouseX, float mouseY) {
		this.mouseX = mouseX;
		this.mouseY = mouseY;
		//System.out.println("Mouse position: (" + mouseX + ", " + mouseY + ")");
	}

	public void updateMouse() {
		updateMousePosition(mouseX, mouseY);
		processInput();
	}

	public TextButton createButton(String text, int index, float x, float y, float width, float height, String styleName) {
		return output.createButton(text, index, x, y, width, height, styleName);
	}

	// Method to handle button clicks
	// Method to handle button clicks
	public void handleButtonClick(int buttonIndex) {
		switch (buttonIndex) {
			case 0:
				// Handle button 1 click
				// Example: Change to another scene
				//game.setScreen(new AnotherScene(game));
				System.out.println("Button clicked: " + buttonIndex);
				sceneManager.showGameScene();
				break;
			case 1:
				// Handle button 2 clickound
				//				soundManager.toggleS
				// Example: Toggle sound();
				System.out.println("Button clicked: " + buttonIndex);
				soundManager.toggleMusic();
				break;
			case 2:
				// Handle button 3 click
				// Example: Exit the game
				System.out.println("Button clicked: " + buttonIndex);
				Gdx.app.exit();
				break;
			// Add cases for other buttons as needed
			case 3:
				// Handle button 3 click
				// Example: Exit the game
				System.out.println("Button clicked: " + buttonIndex);
				//Gdx.app.exit();
				//sceneManager.pauseGameSceneAndSwitchToMenu();
				sceneManager.showMenuScene();
				break;
			// Add cases for other buttons as needed
		}
	}

	// Method to process input events
	public void processInput() {
		// Example: Check for touch events and detect button clicks
		if (Gdx.input.justTouched()) {
			float touchX = Gdx.input.getX();
			float touchY = Gdx.input.getY();
			int buttonIndex = detectClickedButton(touchX, touchY);
			if (buttonIndex != -1) {
				handleButtonClick(buttonIndex);
			}
		}
	}

	// Method to detect which button was clicked based on touch coordinates
	// Method to detect which button was clicked based on touch coordinates
	private int detectClickedButton(float touchX, float touchY) {
		int buttonIndex = -1;
		float buttonWidth = 200f; // Width of each button
		float buttonHeight = 50f; // Height of each button
		float buttonYDiff = 100f; // Difference in y-coordinate between buttons

		float buttonGameWidth = 300f; // Width of each button
		float buttonGameHeight = 100f; // Height of each button

		// Adjust the x and y coordinates based on the button index
		float buttonX = SCREEN_WIDTH - buttonGameWidth - 20; // 20 is the padding from the right edge
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
		}


		return buttonIndex;
	}

}
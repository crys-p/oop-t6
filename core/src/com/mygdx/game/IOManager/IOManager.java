package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.*;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SceneManager.Scene;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SoundManager.SoundManager;

public class IOManager implements InputProcessor {
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	private Scene currentScene; // Field to store the current scene type

	private Output output;
	private Input input;
	private SoundManager soundManager; // Reference to SoundManager
	private PlayerControlManager playerControlManager;
	private SceneManager sceneManager;

	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private float mouseX;
	private float mouseY;
	private boolean leftButtonPressed;
	private boolean rightButtonPressed;

	private Map<Integer, Boolean> keyStates = new HashMap<>();

	Set<Integer> keysAccepted = new HashSet<>(Arrays.asList(
			Input.Keys.LEFT,
			Input.Keys.RIGHT,
			Input.Keys.UP,
			Input.Keys.DOWN,
			Input.Keys.A,
			Input.Keys.D,
			Input.Keys.W,
			Input.Keys.S
	));


	public IOManager (int numButtons, SoundManager soundManager, PlayerControlManager playerControlManager, SceneManager sceneManager) {
		Gdx.input.setInputProcessor(this); // Set IOManager as Input Processor
		output = new Output(numButtons, playerControlManager);
		this.soundManager = soundManager;
		this.playerControlManager = playerControlManager;
		this.sceneManager = sceneManager;
		setUpKeyStates();
	}

	private void setUpKeyStates() {
		for (int keycode : keysAccepted) {
			keyStates.put(keycode, false);
		}
	}

	// Keys
	@Override
	public boolean keyDown(int keycode) {
		if (keysAccepted.contains(keycode)) {
			handleKeyPress(keycode, true);
		}
		return true; // To indicate event was handled
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keysAccepted.contains(keycode)) {
			handleKeyPress(keycode, false);
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	// Set key states based on pressed keys
	public void handleKeyPress(int keycode, boolean isPressed) {
		keyStates.put(keycode, isPressed);
		updateMovement();
	}

	// Activate player control when movement is updated
	public void updateMovement() {
		List<Integer> keys = new ArrayList<>();
		for (Map.Entry<Integer, Boolean> entry : keyStates.entrySet()) {
			if (entry.getValue()) {
				keys.add(entry.getKey());
			}
		}
		if (!keys.isEmpty()) {
			playerControlManager.handlePressedKeys(keys);
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
		//processInput();
	}


	public void displayPlayerInformation(SpriteBatch batch, ShapeRenderer shape) {
		output.displayHealthBar(shape, batch);
		output.displayInventory(batch);
	}

	public TextButton createButton(String text, int index, float x, float y, float width, float height, String styleName) {
		return output.createButton(text, index, x, y, width, height, styleName);
	}

	// creation of timer button
	public TextButton createCountdownButton(String text, int index, float x, float y, float width, float height, String styleName, int countdownSeconds) {
		return output.createCountdownButton(text, index, x, y, width, height, styleName, countdownSeconds);
	}

	public TextButton createButtonNoIndex(String text, float x, float y, float width, float height, String styleName) {
		return output.createButtonNoIndex(text, x, y, width, height, styleName);
	}

	// Method to handle button clicks
	public void handleButtonClick(int buttonIndex) {
		switch (buttonIndex) {
			case 0:
				// Handle button 1 click
				// Example: Change to another scene
				//game.setScreen(new AnotherScene(game));
				System.out.println("Button clicked: " + buttonIndex);
				sceneManager.showScene(SceneManager.SceneType.GAME);
				break;
			case 1:
				// Handle button 2 clickound
				//				soundManager.toggleS
				// Example: Toggle sound();
				System.out.println("Button clicked: " + buttonIndex);
				SceneManager.SceneType currentSceneType = sceneManager.getCurrentSceneType();
				soundManager.toggleMusic(currentSceneType);
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
				sceneManager.showScene(SceneManager.SceneType.MENU);
				break;
			case 4:
				// Handle button 2 clickound
				//				soundManager.toggleS
				// Example: Toggle sound();
				System.out.println("Button clicked: " + buttonIndex);
				SceneManager.SceneType currentSceneType1 = sceneManager.getCurrentSceneType();
				soundManager.toggleMusic(currentSceneType1);
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

		float buttonGameWidth = 200f; // Width of each button
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
		} else if (touchX >= buttonX && touchX <= buttonX + buttonGameWidth && touchY >= SCREEN_HEIGHT - buttonGameHeight - 20 && touchY <= SCREEN_HEIGHT - 20) {
			buttonIndex = 4;
		}


		return buttonIndex;
	}

	public void setSceneMgr(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
}
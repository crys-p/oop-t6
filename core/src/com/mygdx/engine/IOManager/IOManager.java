package com.mygdx.engine.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.*;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.player.GamePlayerManager;

public class IOManager{
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280; //1280
	public static final int SCREEN_HEIGHT = 720;

	private Output output;
	private Input input;
	private SoundManager soundManager; // Reference to SoundManager
	private final GamePlayerManager gameplayerManager;
	private SceneManager sceneManager;

	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private SimulationManager simulationManager;

	public IOManager (int numButtons, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
		this.input = new Input();
		this.output = new Output(numButtons, gameplayerManager);
		this.soundManager = soundManager;
		this.gameplayerManager = gameplayerManager;
		this.sceneManager = sceneManager;
		input.setUpKeyStates();

		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("IOManager initialized"); // Log initialization message
	}

	// Activate player control when movement is updated
	public void updateMovement() {
		List<Integer> keys = new ArrayList<>();
		for (Map.Entry<Integer, Boolean> entry : input.keyboard.keyStates.entrySet()) {
			if (entry.getValue()) {
				keys.add(entry.getKey());
			}
		}
		if (!keys.isEmpty()) {
			gameplayerManager.handlePressedKeys(keys);
		}
	}

	public void displayL1(SpriteBatch batch, ShapeRenderer shape) {
		output.displayHealthBar(shape, batch);
		output.displayLevel1Text(batch);
	}

	public void displayL2(SpriteBatch batch, ShapeRenderer shape) {
		output.displayHealthBar(shape, batch);
		output.displayLevel2Text(batch);
	}

	public void displayL3(SpriteBatch batch, ShapeRenderer shape) {
		output.displayHealthBar(shape, batch);
		output.displayLevel3Text(batch);
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
				sceneManager.showScene(SceneManager.SceneType.GAMEL1);
				break;
			case 1:
				// Handle button 2 clickound
				//				soundManager.toggleS
				// Example: Toggle sound();
				SceneManager.SceneType currentSceneType = sceneManager.getCurrentSceneType();
				soundManager.toggleMusic(currentSceneType);
				break;
			case 2:
				// Handle button 3 click
				// Example: Exit the game
				Gdx.app.exit();
				break;
			// Add cases for other buttons as needed
			case 3:
				// Handle button 3 click
				// Example: Exit the game
				//Gdx.app.exit();
				//sceneManager.pauseGameSceneAndSwitchToMenu();
				sceneManager.showScene(SceneManager.SceneType.MENU);
				break;
			case 4:
				// Handle button 2 clickound
				//				soundManager.toggleS
				// Example: Toggle sound();
				SceneManager.SceneType currentSceneType1 = sceneManager.getCurrentSceneType();
				soundManager.toggleMusic(currentSceneType1);
				break;
			case 5:
				// Handle button 3 click
				// Example: Exit the game
				//Gdx.app.exit();
				//sceneManager.pauseGameSceneAndSwitchToMenu();
				sceneManager.showScene(SceneManager.SceneType.GAMEL1);
				break;
			// Add cases for other buttons as needed
		}
	}

	// Method to process input events
	public void processInput() {
		// Example: Check for touch events and detect button clicks
		if (Gdx.input.justTouched()) {
			float touchX = input.mouse.getMouseX();
			float touchY = input.mouse.getMouseY();
			int buttonIndex = input.detectClickedButton(touchX, touchY);
			if (buttonIndex != -1) {
				handleButtonClick(buttonIndex);
			}
		}
	}

	public void setSceneMgr(SceneManager sceneManager) {
		this.sceneManager = sceneManager;
	}
}
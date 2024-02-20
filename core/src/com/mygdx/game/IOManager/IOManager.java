package com.mygdx.game.IOManager;


import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityManager.EntityManager;
import java.util.ArrayList;
import java.util.List;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.SoundManager.SoundManager;

public class IOManager {
	//initialize from GameMaster?
	public static final int SCREEN_WIDTH = 1280;
	public static final int SCREEN_HEIGHT = 720;
	private Output output;
	private Input input;
	private SoundManager soundManager; // Reference to SoundManager
	//Setting initial size of window
	public void setWindowedMode() {
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private List<Input> inputList;
	//Reference to EntityManager
	private EntityManager entityManager;
	
	public IOManager(int numButtons, SoundManager soundManager) {
		inputList = new ArrayList<>();
		output = new Output(numButtons);
		this.soundManager = soundManager;

	}

	public Output getOutput() {
		return output;
	}

	//Add input to list
	public void addInput(Input input) {
		inputList.add(input);
	}

	//Update all input in the list and communicate with EntityManager
	public void updateInputs() {
		for (Input input : inputList) {
			input.update();
			List<Integer> keys = input.getKeys();
			entityManager.inputMovement(keys);
		}
	}

	public TextButton createButton(String text, int index, float x, float y, float width, float height) {
		return output.createButton(text, index, x, y, width, height);
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
				Gdx.app.exit();
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

		// Check if the touch coordinates are within the bounds of each button
		// Adjust the y-coordinate bounds based on the button index
		if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 300 + buttonIndex * buttonYDiff && touchY <= 300 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 0;
		} else if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 400 + buttonIndex * buttonYDiff && touchY <= 400 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 1;
		} else if (touchX >= 500 && touchX <= 550 + buttonWidth && touchY >= 500 + buttonIndex * buttonYDiff && touchY <= 500 + buttonIndex * buttonYDiff + buttonHeight) {
			buttonIndex = 2;
		}

		return buttonIndex;
	}





}
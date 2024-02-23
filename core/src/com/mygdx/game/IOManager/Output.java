package com.mygdx.game.IOManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;

class Output {
	protected TextButton[] buttons;
	protected Skin skin;

	private HealthBar healthBar;
	private InventoryDisplay inventoryDisplay;

	protected Output(int numButtons, PlayerControlManager playerControlManager) {
		buttons = new TextButton[numButtons];
		skin = new Skin();
		createSkin();
		healthBar = new HealthBar(playerControlManager);
		inventoryDisplay = new InventoryDisplay(playerControlManager);
	}

	protected void createSkin() {
		// Load button textures
		Texture buttonMenu = new Texture("menubutton.png");
		Texture buttonGame = new Texture("gamebutton.png");
		Texture buttonStart = new Texture("menuButton.png"); // Ensure this is the correct texture
		// Load button texture
		Texture buttonTexture = new Texture("menuButton.png");
		Texture buttonLose = new Texture("losebutton.png");
		Texture buttonVictory = new Texture("victorybutton.png");

		// Define button styles for each scene
		TextButtonStyle buttonStyleMenu = new TextButtonStyle();
		buttonStyleMenu.font = new BitmapFont();
		buttonStyleMenu.fontColor = Color.WHITE;
		buttonStyleMenu.up = new TextureRegionDrawable(new TextureRegion(buttonMenu));

		TextButtonStyle buttonStyleGame = new TextButtonStyle();
		buttonStyleGame.font = new BitmapFont();
		buttonStyleGame.fontColor = Color.WHITE;
		buttonStyleGame.up = new TextureRegionDrawable(new TextureRegion(buttonGame));

		TextButtonStyle buttonStyleStart = new TextButtonStyle();
		buttonStyleStart.font = new BitmapFont();
		buttonStyleStart.fontColor = Color.WHITE;
		buttonStyleStart.up = new TextureRegionDrawable(new TextureRegion(buttonStart));

		// Define button style
		TextButtonStyle buttonStyleTimer = new TextButtonStyle();
		buttonStyleTimer.font = new BitmapFont();
		buttonStyleTimer.fontColor = Color.WHITE;
		buttonStyleTimer.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

		// Define button style on tje LoseScene
		TextButtonStyle buttonStyleLose = new TextButtonStyle();
		buttonStyleLose.font = new BitmapFont();
		buttonStyleLose.fontColor = Color.WHITE;
		buttonStyleLose.up = new TextureRegionDrawable(new TextureRegion(buttonLose));

		// Define button style in the VictoryScene
		TextButtonStyle buttonStyleVictory = new TextButtonStyle();
		buttonStyleVictory.font = new BitmapFont();
		buttonStyleVictory.fontColor = Color.WHITE;
		buttonStyleVictory.up = new TextureRegionDrawable(new TextureRegion(buttonVictory));

		// Add button styles to the skin with unique names
		skin.add("buttonMenuStyle", buttonStyleMenu);
		skin.add("buttonGameStyle", buttonStyleGame);
		skin.add("buttonStartStyle", buttonStyleStart);
		// Add button style to the skin
		skin.add("countdownButtonStyle", buttonStyleTimer);
		skin.add("loseButtonStyle", buttonStyleLose);
		skin.add("victoryButtonStyle", buttonStyleVictory);
	}
	protected TextButton createButton(String text, int index, float x, float y, float width, float height, String styleName) {
		buttons[index] = new TextButton(text, skin, styleName);
		buttons[index].setPosition(x, y);
		buttons[index].setSize(width, height);
		return buttons[index];
	}

	protected TextButton createButtonNoIndex(String text, float x, float y, float width, float height, String styleName) {
		TextButton button = new TextButton(text, skin, styleName);
		button.setPosition(x, y);
		button.setSize(width, height);
		return button;
	}


	protected TextButton createCountdownButton(String text, int index, float x, float y, float width, float height, String styleName, int countdownSeconds) {
		// Create the button
		TextButton countdownButton = new TextButton(text, skin, styleName);

		// Set the position and size of the button
		countdownButton.setPosition(x, y);
		countdownButton.setSize(width, height);

		// Start countdown timer
		startCountdown(countdownButton, countdownSeconds);

		return countdownButton;
	}
	protected void startCountdown(TextButton countdownButton, int countdownSeconds) {
		Thread countdownThread = new Thread(() -> {
			try {
				for (int i = countdownSeconds; i >= 0; i--) {
					updateButtonText(countdownButton, i);
					Thread.sleep(1000); // Sleep for one second
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		countdownThread.start();
	}

	protected void updateButtonText(TextButton button, int secondsLeft) {
		button.setText("Countdown: " + secondsLeft);
	}


	protected TextButton getButton(int index) {
		return buttons[index];
	}


	protected void displayHealthBar(ShapeRenderer shape, SpriteBatch batch) {
		healthBar.render(shape, batch);
	}

	protected void displayInventory(SpriteBatch batch) {
		inventoryDisplay.render(batch);
	}
}
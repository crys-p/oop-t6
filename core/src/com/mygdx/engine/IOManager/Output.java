package com.mygdx.engine.IOManager;

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
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.game.IO.HealthBar;
import com.mygdx.game.player.GamePlayerManager;

class Output {
	protected TextButton[] buttons;
	protected Skin skin;

	private HealthBar healthBar;
	private InventoryDisplay inventoryDisplay;

	protected Output(int numButtons, GamePlayerManager gameplayerManager) {
		buttons = new TextButton[numButtons];
		skin = new Skin();
		createSkin();
		healthBar = new HealthBar(gameplayerManager);
		inventoryDisplay = new InventoryDisplay(gameplayerManager);
	}

	protected void createSkin() {
		// to define the properties for each button style in a structure
		String[][] buttonProperties = {
				{"menubutton.png", "buttonMenuStyle"},
				{"gamebutton.png", "buttonGameStyle"},
				{"menuButton.png", "buttonStartStyle"},
				{"menuButton.png", "countdownButtonStyle"},
				{"losebutton.png", "loseButtonStyle"},
				{"victorybutton.png", "victoryButtonStyle"}
		};

		// create and add each button style to the skin
		for (String[] properties : buttonProperties) {
			String textureFile = properties[0];
			String styleName = properties[1];

			// Loading of texture and create style
			Texture buttonTexture = new Texture(textureFile);
			TextButtonStyle buttonStyle = new TextButtonStyle();
			buttonStyle.font = new BitmapFont();
			buttonStyle.fontColor = Color.WHITE;
			buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTexture));

			// Add style to skin
			skin.add(styleName, buttonStyle);
		}
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
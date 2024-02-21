package com.mygdx.game.IOManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Output {
	private TextButton[] buttons;
	private Skin skin;

	public Output(int numButtons) {
		buttons = new TextButton[numButtons];
		skin = new Skin();
		createSkin();
	}

	private void createSkin() {
		// Load button textures
		Texture buttonMenu = new Texture("menubutton.png");
		Texture buttonGame = new Texture("gamebutton.png");
		Texture buttonStart = new Texture("menuButton.png"); // Ensure this is the correct texture

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

		// Add button styles to the skin with unique names
		skin.add("buttonMenuStyle", buttonStyleMenu);
		skin.add("buttonGameStyle", buttonStyleGame);
		skin.add("buttonStartStyle", buttonStyleStart);
	}
	public TextButton createButton(String text, int index, float x, float y, float width, float height, String styleName) {
		buttons[index] = new TextButton(text, skin, styleName);
		buttons[index].setPosition(x, y);
		buttons[index].setSize(width, height);
		return buttons[index];
	}



	public TextButton getButton(int index) {
		return buttons[index];
	}

	// Other methods for managing buttons
}

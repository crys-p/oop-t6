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
		Texture buttonTextureUp = new Texture("button.png");
		Texture buttonTextureDown = new Texture("button.png");

		// Define button styles
		TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
		buttonStyle.font = new BitmapFont(); // Set font directly
		buttonStyle.fontColor = Color.WHITE; // Set font color directly
		buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTextureUp));
		buttonStyle.down = new TextureRegionDrawable(new TextureRegion(buttonTextureDown));
		skin.add("default", buttonStyle);
	}

	public TextButton createButton(String text, int index, float x, float y, float width, float height) {
		buttons[index] = new TextButton(text, skin);
		buttons[index].setPosition(x, y);
		buttons[index].setSize(width, height);
		return buttons[index];
	}



	public TextButton getButton(int index) {
		return buttons[index];
	}

	// Other methods for managing buttons
}

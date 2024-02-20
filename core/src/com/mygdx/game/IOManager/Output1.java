package com.mygdx.game.IOManager;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Output1 {
    private SpriteBatch batch;
    private ShapeRenderer shape;
    private Skin skin;

    public Output1() {
        batch = new SpriteBatch();
        shape = new ShapeRenderer();
        skin = new Skin();
        createSkin();
    }

    private void createSkin() {
        // Load button textures
        Texture buttonTextureUp = new Texture("button.png");
        Texture buttonTextureDown = new Texture("button.png");

        // Define button styles
        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = new TextureRegionDrawable(new TextureRegion(buttonTextureUp));
        buttonStyle.down = new TextureRegionDrawable(new TextureRegion(buttonTextureDown));
        skin.add("default", buttonStyle);
    }

    public TextButton createButton(String text) {
        return new TextButton(text, skin);
    }

    // Other methods for rendering and displaying UI elements
}

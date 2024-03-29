package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class LoseScene extends Scene {


    private Texture backgroundTexture;

    protected TextButton button;
    public LoseScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);

        setBackgroundColor(Color.RED); // setting of background color for end scene
        backgroundTexture = new Texture(Gdx.files.internal("assets/losescene.png"));
    }

    protected void createButtons() {
        // Define width and height for the buttons (you can adjust these values as needed)
        float buttonWidth = 300f;
        float buttonHeight = 100f;

        // Define button positions
        float buttonX = (IOManager.SCREEN_WIDTH - buttonWidth) / 2; // Centered horizontally
        float buttonY1 = 50; // Adjust this value for the first button's vertical position


        // Create buttons using the IOManager
        button = ioManager.createButton("Retry Game", 0, buttonX, buttonY1, buttonWidth, buttonHeight, "buttonMenuStyle");


        // Add click listeners to the buttons
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 1
                int buttonIndex = 5; // Assuming button 1 is at index 0
                ioManager.handleButtonClick(buttonIndex);
            }
        });
        // Set button positions and add listeners if needed
    }
    // Other methods for managing the menu scene

    @Override
    public void show() {
        //createEntities();
        createButtons();
    }

    @Override
    protected void createEntities() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        //createButtonNoIndex.draw(batch, 1);
        button.draw(batch, 1); // Adjust parameters as needed
        batch.end();

        // Process input events
        ioManager.processInput();
    }

}

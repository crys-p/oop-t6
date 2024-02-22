package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;

import java.util.Random;

public class MenuScene extends Scene {

    private TextButton button1;
    private TextButton button2;
    private TextButton button3;

    public MenuScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.YELLOW); // setting of background color for end scene

        //createButtons();
    }

    @Override
    public void show() {
        // Logic when the game scene is shown
        createButtons();
        createEntities();
    }

    @Override
    public void createEntities() {

    }

    @Override
    public void hide() {
        // Logic when the game scene is hidden
    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        button1.draw(batch, 1); // Adjust parameters as needed
        button2.draw(batch, 1);
        button3.draw(batch, 1);
        batch.end();
        // Rendering logic for the game scene

        // Process input events
        ioManager.processInput();
        //Gdx.gl.glClearColor(getBackgroundColor().r, getBackgroundColor().g, getBackgroundColor().b, getBackgroundColor().a);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }

    private void createButtons() {
        // Define width and height for the buttons (you can adjust these values as needed)
        float buttonWidth = 200f;
        float buttonHeight = 50f;

        // Define button positions
        float buttonX = (IOManager.SCREEN_WIDTH - buttonWidth) / 2; // Centered horizontally
        float buttonY1 = 500; // Adjust this value for the first button's vertical position
        float buttonY2 = buttonY1 - 100; // Adjust this value for the second button's vertical position
        float buttonY3 = buttonY2 - 100; // Adjust this value for the third button's vertical position

        // Create buttons using the IOManager
        button1 = ioManager.createButton("Restart", 0, buttonX, buttonY1, buttonWidth, buttonHeight, "buttonMenuStyle");
        button2 = ioManager.createButton("Toogle Sound", 1, buttonX, buttonY2, buttonWidth, buttonHeight, "buttonMenuStyle");
        button3 = ioManager.createButton("EXIT GAME", 2, buttonX, buttonY3, buttonWidth, buttonHeight, "buttonMenuStyle");

        // Add click listeners to the buttons
        button1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 1
                int buttonIndex = 0; // Assuming button 1 is at index 0
                ioManager.handleButtonClick(buttonIndex);
            }
        });

        button2.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 2
                int buttonIndex = 1; // Assuming button 2 is at index 1
                ioManager.handleButtonClick(buttonIndex);
            }
        });

        button3.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 3
                int buttonIndex = 2; // Assuming button 3 is at index 2
                ioManager.handleButtonClick(buttonIndex);
            }
        });
        // Set button positions and add listeners if needed
    }
    // Other methods for managing the menu scene

    @Override
    public void pause() {
        super.pause();
        // Additional logic to pause the game scene
    }

    @Override
    public void resume() {
        super.resume();
        // Additional logic to resume the game scene
    }




}
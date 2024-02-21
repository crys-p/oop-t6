package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.GameMaster;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.SimulationManager.SimulationManager;

import java.util.Random;

public class GameScene extends Scene {

    private TextButton gameSceneButton;

    public GameScene(Game game, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.BLUE); // setting of background color for end scene
    }

    @Override
    public void show() {
        // Logic when the game scene is shown
        createEntities();
        createButtons();
    }

    @Override
    public void createEntities() {

        entityManager.createCharacter(1, 100, 0, 400, 20, "UDLR");

        Random random = new Random();
        entityManager.createCollectibleRandom(10, random, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight(), -10, 10, -10, 10);
        entityManager.createEnemyRandomY(10, random, 0, Gdx.graphics.getWidth(), 0, Gdx.graphics.getHeight(), -10, 10, -10, 10);

        entityManager.logAll(); // for debugging
    }

    private void createButtons() {
        // Define width and height for the buttons (you can adjust these values as needed)
        float buttonWidth = 300f;
        float buttonHeight = 100f;

        // Position the button at the top right corner
        float buttonX = IOManager.SCREEN_WIDTH - buttonWidth - 20; // 20 is the padding from the right edge
        float buttonY = IOManager.SCREEN_HEIGHT - buttonHeight - 20; // 20 is the padding from the top edge

        // Create buttons using the IOManager
        gameSceneButton = ioManager.createButton("MENU", 3, buttonX, buttonY, buttonWidth, buttonHeight, "buttonGameStyle");


        // Add click listeners to the buttons
        gameSceneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 3
                int buttonIndex = 3; // Assuming button 1 is at index 3
                ioManager.handleButtonClick(buttonIndex);
            }
        });
        // Set button positions and add listeners if needed
    }


    @Override
    public void hide() {
        // Logic when the game scene is hidden
    }

    @Override
    public void render(float delta) {
        // Rendering logic for the game scene
        clearScreen();
        batch.begin();
        gameSceneButton.draw(batch, 1); // Adjust parameters as needed
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.end();
        entityManager.drawAllEntities(batch);
        batch.end();

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
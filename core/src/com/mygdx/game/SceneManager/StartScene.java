package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;

import java.util.Random;


import static com.mygdx.game.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.game.IOManager.IOManager.SCREEN_WIDTH;

public class StartScene extends Scene {

    private TextButton countdownButton;

    public StartScene(Game game, EntityManager entityManager, SpriteBatch batch,ShapeRenderer shape, IOManager ioManager) {
        super(game, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.GRAY); // Set background color for start scene
        this.entityManager = entityManager;
    }

    @Override
    public void show() {
        //createEntities();
        createCountdownButton();
    }

    @Override
    protected void createEntities() {
        // Create entities specific to the start scenex
        entityManager.createCharacter(1, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 20, 0, null);
    }

    private void createCountdownButton() {
        countdownButton = ioManager.createCountdownButton("Start Countdown", 0, IOManager.SCREEN_WIDTH / 2 - 100, IOManager.SCREEN_HEIGHT / 2 - 25, 200, 50, "buttonStartStyle", 10); // Set the countdown duration to 10 seconds
    }



    @Override
    public void hide() {
        // Logic when the start scene is hidden
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        clearScreen();
        batch.begin();
        countdownButton.draw(batch, 1);
        entityManager.drawAllEntities(batch);
        batch.end();
        // Update the camera and viewport
        //camera.update();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }
}
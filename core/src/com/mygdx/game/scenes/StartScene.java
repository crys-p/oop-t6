package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;

import java.util.Random;


import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class StartScene extends Scene {

    protected TextButton countdownButton;

    public StartScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.GRAY); // Set background color for start scene
        this.entityManager = entityManager;
    }

    @Override
    public void show() {
        //createEntities();
        createCountdownButton();
        // After 10 seconds, switch to the GameScene
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameScene after 5 seconds");
                sceneManager.showScene(SceneManager.SceneType.GAME);
            }
        }, 5); // Delay of 10 seconds
    }

    @Override
    protected void createEntities() {
        // Create entities specific to the start scenex
        entityManager.createCharacter(1, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, 20);
    }

    protected void createCountdownButton() {
        countdownButton = ioManager.createCountdownButton("Start Countdown", 0, IOManager.SCREEN_WIDTH / 2 - 100, IOManager.SCREEN_HEIGHT / 2 - 25, 200, 50, "countdownButtonStyle", 5); // Set the countdown duration to 10 seconds
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
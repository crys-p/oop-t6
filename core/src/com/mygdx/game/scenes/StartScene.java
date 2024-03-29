package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    private Texture backgroundTexture;

    public StartScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.GRAY); // Set background color for start scene
        this.entityManager = entityManager;

        backgroundTexture = new Texture(Gdx.files.internal("assets/startscene.png"));
    }

    @Override
    public void show() {
        //createEntities();
        createCountdownButton();
        // After 10 seconds, switch to the GameSceneL1
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameSceneL1 after 5 seconds");
                sceneManager.showScene(SceneManager.SceneType.GAMEL3);
            }
        }, 3); // Delay of 10 seconds
    }

    @Override
    protected void createEntities() {
        // Create entities specific to the start scene
    }

    protected void createCountdownButton() {
        countdownButton = ioManager.createCountdownButton("Start Countdown", 0, IOManager.SCREEN_WIDTH / 2 - 250, IOManager.SCREEN_HEIGHT / 2 - 300, 500, 500, "countdownButtonStyle", 2); // Set the countdown duration to 10 seconds

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
        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
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
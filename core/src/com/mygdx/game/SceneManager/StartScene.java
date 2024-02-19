package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;

public class StartScene extends Scene {

    private SceneManager sceneManager;

    public StartScene(Game game, SceneManager sceneManager) {
        super(game);
        setBackgroundColor(Color.GRAY); // Set background color for start scene
        this.sceneManager = sceneManager;
    }

    @Override
    public void show() {

        // After 10 seconds, switch to the GameScene
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameScene after 10 seconds");
                sceneManager.showGameScene();
            }
        }, 2); // Delay of 10 seconds


    }

    @Override
    public void hide() {
        // Logic when the start scene is hidden
    }

    @Override
    public void render(float delta) {
        Gdx.app.log("Start scene", "rendering!");

        // Clear the screen
        clearScreen();


        // Update the camera and viewport
        //camera.update();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }
}

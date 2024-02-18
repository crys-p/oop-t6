package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.mygdx.game.EntityManager.EntityManager;

public class SceneManager {
    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private Scene currentScene;

    public SceneManager(Game game) {
        this.game = game;
        initializeScenes();
    }

    private void initializeScenes() {
        startScene = new StartScene(game);
        gameScene = new GameScene(game);
        currentScene = startScene; // Set the initial scene
    }

    public void showStartScene() {
        changeScene(startScene);
        // for adding of entities specific to the game scene
        //entityManager.createDroplet();
        //entityManager.createBucket();
        //entityManager.createTriangle();
        //entityManager.createCircle();

    }

    public void showGameScene() {
        changeScene(gameScene);
    }

    private void changeScene(Scene newScene) {
        disposeCurrentScene();
        currentScene = newScene;
        game.setScreen(currentScene);
        System.out.println("Scene changed to: " + newScene.getClass().getSimpleName());
    }

    private void disposeCurrentScene() {
        if (currentScene != null) {
            currentScene.dispose();
            System.out.println("Disposed of previous scene: " + currentScene.getClass().getSimpleName());
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }
}


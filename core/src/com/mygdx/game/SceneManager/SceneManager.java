package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.SimulationManager.SimulationManager;

public class SceneManager {
    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private Scene currentScene;
    private SimulationManager simulationManager;

    public SceneManager(Game game) {
        this.game = game;
        initializeScenes();
        // Initialize SimulationManager
        this.simulationManager = SimulationManager.getInstance();
        simulationManager.logInfo("SceneManager initialized");
    }

    private void initializeScenes() {
        startScene = new StartScene(game);
        gameScene = new GameScene(game); // Ensure gameScene is initialized correctly
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


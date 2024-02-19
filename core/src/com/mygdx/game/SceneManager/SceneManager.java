package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.EntityManager.Entity;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.SimulationManager.SimulationManager;

import java.util.Random;

public class SceneManager {
    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private Scene currentScene;

    private SimulationManager simulationManager;

    private EntityManager entityManager;


    public SceneManager(Game game) {
        this.game = game;
        initializeScenes();
        //Initialize SimulationManager
        //this.simulationManager = SimulationManager.getInstance(); // this is to log the Scene change
        //simulationManager.logInfo("SceneManager initialized"); // This to log the Scene change

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("SceneManager initialized"); // Log initialization message

    }


    public SceneManager(Game game, EntityManager entityManager) {
        this.game = game;
        this.entityManager = entityManager;
        initializeScenes();

        //this.simulationManager = SimulationManager.getInstance();
        //simulationManager.logInfo("SceneManager initialized");

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("SceneManager initialized"); // Log initialization message

    }

    private void initializeScenes() {
        startScene = new StartScene(game, this, entityManager);
        gameScene = new GameScene(game); // Ensure gameScene is initialized correctly
        currentScene = startScene; // Set the initial scene
    }

    public void showStartScene() {
        changeScene(startScene);
        // Log initialization message
        //simulationManager.logInfo("StartScene initialized");



        // After 10 seconds, switch to the GameScene
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameScene after 10 seconds");
                showGameScene();
            }
        }, 5); // Delay of 10 seconds

    }

    public void showGameScene() {
        changeScene(gameScene);
        // Log initialization message
        simulationManager.logInfo("GameScene initialized");

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


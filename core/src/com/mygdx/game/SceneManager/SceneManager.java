package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
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
        startScene = new StartScene(game);
        gameScene = new GameScene(game); // Ensure gameScene is initialized correctly
        currentScene = startScene; // Set the initial scene

    }

    public void showStartScene() {
        changeScene(startScene);
        // Log initialization message
        //simulationManager.logInfo("StartScene initialized");
        // for adding of entities specific to the game scene
        entityManager.createCharacter(1, 100, -100, 20, 0);
        Random random = new Random();
        entityManager.createItemRandomX(10, random, 680, 0, 20);
        entityManager.createTriangle(1, 300, 200, 40, 40, Color.GREEN, 50);
        entityManager.createCircle(1, 200, 300, 400, 0, Color.RED, 50);
        entityManager.logAll(); // for debugging
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


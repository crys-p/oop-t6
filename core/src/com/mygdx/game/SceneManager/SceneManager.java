package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.EntityManager.Entity;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.Random;

public class SceneManager {
    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private Scene currentScene;
    private EntityManager entityManager;

    public SceneManager(Game game) {
        this.game = game;
        initializeScenes();
    }

    public SceneManager(Game game, EntityManager entityManager) {
        this.game = game;
        this.entityManager = entityManager;
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
        entityManager.createCharacter(1, 100, -100, 20, 0);
        Random random = new Random();
        entityManager.createItemRandomX(10, random, 680, 0, 20);
        entityManager.createTriangle(1, 300, 200, 40, 40, Color.GREEN, 50);
        entityManager.createCircle(1, 200, 300, 400, 0, Color.RED, 50);
        entityManager.logAll();
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


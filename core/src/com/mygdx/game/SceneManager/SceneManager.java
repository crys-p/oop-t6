package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.SimulationManager.SimulationManager;
import com.mygdx.game.SoundManager.SoundManager;

public class SceneManager {
    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private MenuScene menuScene;
    private Scene currentScene;

    private SimulationManager simulationManager;

    private EntityManager entityManager;

    private SoundManager soundManager;
    private IOManager ioManager;


    private CollisionManager collisionManager;
//    public SceneManager(Game game) {
//        this.game = game;
//        initializeScenes();
//        //Initialize SimulationManager
//        //this.simulationManager = SimulationManager.getInstance(); // this is to log the Scene change
//        //simulationManager.logInfo("SceneManager initialized"); // This to log the Scene change
//
//        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
//        simulationManager.logInfo("SceneManager initialized"); // Log initialization message
//
//    }


    public SceneManager(Game game, EntityManager entityManager, IOManager ioManager, SoundManager soundManager ){
        this.game = game;
        this.entityManager = entityManager;
        this.ioManager = ioManager;
        this.soundManager = soundManager; // Use the provided SoundManager instance
        initializeScenes();

        //this.simulationManager = SimulationManager.getInstance();
        //simulationManager.logInfo("SceneManager initialized");

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("SceneManager initialized"); // Log initialization message
        //soundManager = new SoundManager("background_music.mp3", "background_music_2.mp3", "sound_effect.mp3");
    }

    private void initializeScenes() {
        startScene = new StartScene(game, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
        gameScene = new GameScene(game, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager); // Ensure gameScene is initialized correctly
        menuScene = new MenuScene(game, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager); // Ensure gameScene is initialized correctly
        currentScene = null;
    }

    public void showStartScene() {
        changeScene(startScene);
        // play StartScene Song
        soundManager.playStartSceneMusic();
        // Log initialization message
        //simulationManager.logInfo("StartScene initialized");

        // After 10 seconds, switch to the GameScene
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameScene after 2 seconds");
                showGameScene();
            }
        }, 2); // Delay of 10 seconds

    }

    public void showGameScene() {
        //changeScene(gameScene);
        changeScene(menuScene);
        // play the GameScene Song
        soundManager.playGameSceneMusic();
        // Log initialization message
        simulationManager.logInfo("GameScene initialized");

    }

    private void changeScene(Scene newScene) {
        // stop all music before changing the scene
        soundManager.stopAllMusic();
        disposeCurrentScene();
        currentScene = newScene;
        game.setScreen(currentScene);
        System.out.println("Scene changed to: " + newScene.getClass().getSimpleName());
    }

    private void disposeCurrentScene() {
        if (currentScene != null) {
            currentScene.batch.dispose();
            currentScene.shape.dispose();
            currentScene.dispose();
            entityManager.deleteAllEntities();
            System.out.println("Disposed of previous scene: " + currentScene.getClass().getSimpleName());
        }
    }

    public Scene getCurrentScene() {
        return currentScene;
    }

}


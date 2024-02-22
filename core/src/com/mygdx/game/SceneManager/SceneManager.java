package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SimulationManager.SimulationManager;
import com.mygdx.game.SoundManager.SoundManager;

public class SceneManager {
    public enum SceneType {
        START, GAME, MENU, END_WIN, END_LOSE
    }

    private final Game game;
    private StartScene startScene;
    private GameScene gameScene;
    private MenuScene menuScene;
    private LoseScene loseScene;
    private VictoryScene victoryScene;
    private Scene currentScene;

    private SimulationManager simulationManager;

    private EntityManager entityManager;

    private SoundManager soundManager;
    private IOManager ioManager;

    private PlayerControlManager playerControlManager;

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


    public SceneManager(Game game, EntityManager entityManager, IOManager ioManager, SoundManager soundManager, PlayerControlManager playerControlManager){
        this.game = game;
        this.entityManager = entityManager;
        this.ioManager = ioManager;
        this.soundManager = soundManager; // Use the provided SoundManager instance
        this.playerControlManager = playerControlManager;
        initializeScenes();

        //this.simulationManager = SimulationManager.getInstance();
        //simulationManager.logInfo("SceneManager initialized");

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("SceneManager initialized"); // Log initialization message
        //soundManager = new SoundManager("background_music.mp3", "background_music_2.mp3", "sound_effect.mp3");
    }

    private void initializeScenes() {
        startScene = new StartScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
        gameScene = new GameScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager, playerControlManager); // Ensure gameScene is initialized correctly
        menuScene = new MenuScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager); // Ensure gameScene is initialized correctly
        loseScene = new LoseScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
        victoryScene = new VictoryScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
        currentScene = null;
    }

    public void showStartScene() {
        changeScene(startScene);
        // play StartScene Song
        soundManager.playMusic(SceneType.START);
        // Log initialization message
        //simulationManager.logInfo("StartScene initialized");

        // After 10 seconds, switch to the GameScene
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameScene after 2 seconds");
                showGameScene();
            }
        }, 5); // Delay of 10 seconds
    }

    public void showGameScene() {
        //changeScene(menuScene);
        changeScene(gameScene);
        // play the GameScene Song
        soundManager.playMusic(SceneType.GAME);
        // Log initialization message
        simulationManager.logInfo("GameScene initialized");
    }

    public void showMenuScene() {
        changeScene(menuScene);
        // play the GameScene Song
        soundManager.playMusic(SceneType.MENU);
        // Log initialization message
        simulationManager.logInfo("MenuScene initialized");
    }


    public void showLoseScene() {
        changeScene(loseScene);
        // play the GameScene Song
        soundManager.playMusic(SceneType.START);
        // Log initialization message
        simulationManager.logInfo("loseScene initialized");
    }

    public void showVictoryScene() {
        changeScene(victoryScene);
        // play the GameScene Song
        soundManager.playMusic(SceneType.START);
        // Log initialization message
        simulationManager.logInfo("Victory initialized");
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
            currentScene.dispose();
            entityManager.deleteAllEntities();
            System.out.println("Disposed of previous scene: " + currentScene.getClass().getSimpleName());
        }
    }

//    public void pauseGameSceneAndSwitchToMenu() {
//        // Check if the current scene is an instance of GameScene
//        if (currentScene instanceof GameScene) {
//            // Pause the game scene
//            currentScene.pause();
//
//            // Create and set the menu scene
//            MenuScene menuScene = new MenuScene(game, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
//            changeScene(menuScene);
//        } else {
//            System.out.println("Error: Current scene is not a GameScene.");
//        }
//    }

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setScene(Scene scene) {
        currentScene = scene;
        game.setScreen(currentScene);
    }

}

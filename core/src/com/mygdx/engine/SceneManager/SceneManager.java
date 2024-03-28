package com.mygdx.engine.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.GameFactories.EntityFactoryManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.player.GamePlayerManager;
import com.mygdx.game.scenes.*;


import java.util.HashMap;
import java.util.Map;



public class SceneManager {
    public enum SceneType {
        START, GAMEL1, GAMEL2 ,GAMEL3, MENU, VICTORY, LOSE
    }

    private final Game game;
    private Scene currentScene;

    private SimulationManager simulationManager;

    private EntityManager entityManager;

    private SoundManager soundManager;
    private IOManager ioManager;

    private GamePlayerManager gameplayerManager;
    private EntityFactoryManager entityFactoryManager;
    private CameraManager cameraManager;

    protected HashMap<SceneType, Scene> allScenesMap; // factory replace it
    private iSceneFactory sceneFactory; // The scene factory instance
    private SceneType currentSceneType;

    public static int SCENE_WIDTH;
    public static int SCENE_HEIGHT;



    public SceneManager(Game game, EntityManager entityManager, EntityFactoryManager entityFactoryManager, IOManager ioManager, SoundManager soundManager, GamePlayerManager gameplayerManager, CameraManager cameraManager){
        this.game = game;
        this.entityManager = entityManager;
        this.ioManager = ioManager;
        this.soundManager = soundManager; // Use the provided SoundManager instance
        this.gameplayerManager = gameplayerManager;
        this.entityFactoryManager = entityFactoryManager;
        this.cameraManager = cameraManager;
        // allScenesMap = new HashMap<>(); // factory replace it
        // initializeScenes();
        this.sceneFactory = new SceneFactory(); // Initialize the scene factory here

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("SceneManager initialized"); // Log initialization message
        SCENE_WIDTH = Scene.getSceneWidth();
        SCENE_HEIGHT = Scene.getSceneHeight();
    }


    /*
    private void initializeScenes() {
        allScenesMap.put(SceneType.START,
                new StartScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager));
        allScenesMap.put(SceneType.GAME,
                new GameSceneL1(game, this, entityManager, entityFactoryManager, new SpriteBatch(), new ShapeRenderer(), ioManager, gameplayerManager, cameraManager));
        allScenesMap.put(SceneType.MENU,
                new MenuScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager));
        allScenesMap.put(SceneType.LOSE,
                new LoseScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager));
        allScenesMap.put(SceneType.VICTORY,
                new VictoryScene(game, this, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager));
        currentScene = null;
    }
    */

    public void showScene(SceneType sceneType) {

        // Set the current scene type also solve theg sound to work
        setCurrentSceneType(sceneType);

        Scene scene = sceneFactory.createScene(sceneType, game, this, entityManager, entityFactoryManager, ioManager, gameplayerManager, cameraManager);
        //Scene scene = allScenesMap.get(sceneType);
        changeScene(scene);
        soundManager.playMusic(sceneType);
        simulationManager.logInfo(sceneType + " SCENE initialised");
    }


    private void changeScene(Scene newScene) {
        // stop all music before changing the scene
        soundManager.stopAllMusic();
        disposeCurrentScene();
        currentScene = newScene;
        game.setScreen(currentScene);
        // set the current scene type when changing the scene
        setCurrentSceneType(getSceneType(newScene));
        System.out.println("Scene changed to: " + newScene.getClass().getSimpleName());
    }

    private SceneType getSceneType(Scene scene) {
        /*
        for (Map.Entry<SceneType, Scene> entry : allScenesMap.entrySet()) {
            if (entry.getValue() == scene) {
                return entry.getKey();
            }
        }
        return null;
        */
        return currentSceneType; // Placeholder return, adjust based on your actual implementation
    }

    private void disposeCurrentScene() {
        if (currentScene != null) {
            currentScene.dispose();
            entityManager.deleteAllEntities();
            System.out.println("Disposed of previous scene: " + currentScene.getClass().getSimpleName());
        }
    }

//    public void pauseGameSceneAndSwitchToMenu() {
//        // Check if the current scene is an instance of GameSceneL1
//        if (currentScene instanceof GameSceneL1) {
//            // Pause the game scene
//            currentScene.pause();
//
//            // Create and set the menu scene
//            MenuScene menuScene = new MenuScene(game, entityManager, new SpriteBatch(), new ShapeRenderer(), ioManager);
//            changeScene(menuScene);
//        } else {
//            System.out.println("Error: Current scene is not a GameSceneL1.");
//        }
//    }



    public void setCurrentSceneType(SceneType sceneType) {
        this.currentSceneType = sceneType;
    }

    public SceneType getCurrentSceneType() {
        return currentSceneType;
    }

}

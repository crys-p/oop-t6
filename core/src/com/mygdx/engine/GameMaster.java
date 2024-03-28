package com.mygdx.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.engine.CollisionManager.CollisionManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.GameFactories.EntityFactoryManager;
import com.mygdx.game.GameFactories.GameTextureFactory;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundManager; // sound manager

import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.player.PlayerControlConfigs;
import com.mygdx.game.player.GamePlayerManager;

//public class GameMaster extends ApplicationAdapter
public class GameMaster extends Game {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityManager;
	private EntityFactoryManager entityFactoryManager;
	private SceneManager sceneManager;
	private SimulationManager simulationManager; // Add SimulationManager reference
	private SoundManager soundManager;
	private IOManager ioManager;
	private GamePlayerManager gameplayerManager;
	private CollisionManager collisionManager;
	private CameraManager cameraManager;
	private float delta;

	public void create() {
		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("GameMaster initialized"); // Log initialization message

		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Create Texture factory
		GameTextureFactory gameTextureFactory = new GameTextureFactory();
		gameTextureFactory.create();

		// Initialise EntityManager
		entityManager = new EntityManager();
		entityFactoryManager = new EntityFactoryManager(entityManager, gameTextureFactory);

		// Initialise SoundManager with background music and sound effect files
		soundManager = new SoundManager();
		soundManager.createSounds();

		// Initialise PlayerManager
		gameplayerManager = new GamePlayerManager(entityManager);
		// Set up single player mode with default UDLR key controls
		gameplayerManager.createPlayers(1);
		gameplayerManager.setPlayerControl(0, PlayerControlConfigs.UDLR); // UDLR or WASD accepted

		// Initialize IOManager
		ioManager = new IOManager(5, soundManager, gameplayerManager, null);
		ioManager.setWindowedMode(); // Setting the initial size of the window

		// Initialize CameraManager
		cameraManager = new CameraManager(gameplayerManager);

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager, entityFactoryManager, ioManager, soundManager, gameplayerManager, cameraManager);

		// Pass the SceneManager instance to ioManager
		ioManager.setSceneMgr(sceneManager);

		// Initialize Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, gameplayerManager, sceneManager);


		sceneManager.showScene(SceneManager.SceneType.START);

	}

	// Method to switch to another scene
	public void render() {
		super.render();
		entityManager.movement();
		collisionManager.startCollisionDetection();
	}


	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
		super.dispose();
	}

}
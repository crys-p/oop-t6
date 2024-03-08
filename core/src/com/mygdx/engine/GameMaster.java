package com.mygdx.engine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.CollisionManager.CollisionManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundManager; // sound manager

import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.PlayerControlConfigs;

//public class GameMaster extends ApplicationAdapter
public class GameMaster extends Game {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityManager;
	private SceneManager sceneManager;
	private SimulationManager simulationManager; // Add SimulationManager reference
	private SoundManager soundManager;
	private IOManager ioManager;
	private PlayerManager playerManager;
	private CollisionManager collisionManager;

	public void create() {
		// Creating renderers
		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("GameMaster initialized"); // Log initialization message

		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Initialise EntityManager
		entityManager = new EntityManager();

		// Initialise SoundManager with background music and sound effect files
		soundManager = new SoundManager();
		soundManager.createSounds();

		// Initialise PlayerManager
		playerManager = new PlayerManager(entityManager);
		// Set up single player mode with default UDLR key controls
		playerManager.createPlayers(1);
		playerManager.setPlayerControl(0, PlayerControlConfigs.UDLR); // UDLR or WASD accepted

		// Initialize IOManager
		ioManager = new IOManager(5, soundManager, playerManager, null);
		ioManager.setWindowedMode(); // Setting the initial size of the window

		// Initialize Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, playerManager);

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager, ioManager, soundManager, playerManager);
		ioManager.setSceneMgr(sceneManager);
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
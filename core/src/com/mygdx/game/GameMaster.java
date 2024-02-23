package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.IOManager.HealthBar;
import com.mygdx.game.PlayerControlManager.Inventory;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SceneManager.Scene; // Adjust the package name as needed edmund scene
import com.mygdx.game.SoundManager.SoundManager; // sound manager

import com.mygdx.game.SimulationManager.SimulationManager;

//public class GameMaster extends ApplicationAdapter
public class GameMaster extends Game {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityManager;
	private SceneManager sceneManager;
	private SimulationManager simulationManager; // Add SimulationManager reference
	private SoundManager soundManager;
	private IOManager ioManager;
	private PlayerControlManager playerControlManager;
	private CollisionManager collisionManager;


	public void create() {
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Initialise EntityManager
		entityManager = new EntityManager();

		// Initialise SoundManager with background music and sound effect files
		soundManager = new SoundManager();
		soundManager.createSounds();

		// Initialise PlayerControlManager
		playerControlManager = new PlayerControlManager(entityManager);
		// Set up single player mode with default UDLR key controls
		playerControlManager.createPlayers(1);
		playerControlManager.setPlayerControl(0, "UDLR"); // UDLR or WASD accepted

		// Initialize IOManager
		ioManager = new IOManager(5, soundManager, playerControlManager, null);
		ioManager.setWindowedMode(); // Setting the initial size of the window

		// Initialize Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, playerControlManager);

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager, ioManager, soundManager, playerControlManager);
		ioManager.setSceneMgr(sceneManager);
		sceneManager.showScene(SceneManager.SceneType.START);

		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("GameMaster initialized"); // Log initialization message

	}

	// Method to switch to another scene
	public void render() {
		super.render();
		entityManager.movement();
		ioManager.updateMouse();
		collisionManager.startCollisionDetection();
	}

	@Override
	public void dispose() {
		batch.dispose();
		shape.dispose();
		super.dispose();
	}

}
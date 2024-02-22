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
	private Scene currentScene; // storing of the current scene reference
	private SimulationManager simulationManager; // Add SimulationManager reference
	private SoundManager soundManager;
	private IOManager ioManager;
	private PlayerControlManager playerControlManager;
	private HealthBar healthBar;
	private Inventory inventory;
    private CollisionManager collisionManager;

//	private IOManager getIOManager() {
//		if (ioManager == null) {
//			ioManager = new IOManager( 5, soundManager, playerControlManager, sceneManager);
//			ioManager.setWindowedMode();
//		}
//		return ioManager;
//	}

	public void create() {
		// Initialize SoundManager with background music and sound effect files

		soundManager = new SoundManager("background_music.mp3", "background_music_3.mp3","menu_music.mp3","sound_effect.mp3");

		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Create EntityManager
		entityManager = new EntityManager();

		// Create PlayerControlManager and HealthBar instances
		playerControlManager = new PlayerControlManager(entityManager);

		// Set up single player mode with default Up down left right key controls
		playerControlManager.createPlayers(1);
		playerControlManager.setPlayerControl(0, "UDLR"); // udlr or wasd

		//Initialize IOManager
		ioManager = new IOManager(5, soundManager, playerControlManager, null);
		ioManager.setWindowedMode(); // Setting the initial size of the window

		// Initialize Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, playerControlManager);

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager, ioManager, soundManager, playerControlManager);
		ioManager.setSceneMgr(sceneManager);
		sceneManager.showStartScene();

		// Initialize SoundManager with background music and sound effect files
		// Initialize SoundManager with background music file

		// 2 different way to show log 2nd way might be better as log will go to every manager
		// the first code consume unnecessary memory and resources as "this.simulationManager" is only use in logging
		// Initialize SimulationManager
		//this.simulationManager = SimulationManager.getInstance();
		//simulationManager.logInfo("GameMaster initialized");

		simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
		simulationManager.logInfo("GameMaster initialized"); // Log initialization message

	}
	// Method to switch to another scene

	public void render() {
		super.render();

		//System.out.println("Current scene: " + sceneManager.getCurrentScene().getClass().getSimpleName());
	// Call the movement method of the EntityManager to simulate random movement for entity with ID 11 //for testing
		entityManager.movement();

		//entityMgr.setUpMovement();
		entityManager.movement();
		ioManager.updateMovement();
		ioManager.updateMouse();

		// Keep the player within the screen bounds
//		if(player.getX() > Gdx.graphics.getWidth())
//			player.setX(Gdx.graphics.getWidth());
//		if(player.getY() > Gdx.graphics.getHeight())
//			player.setY(Gdx.graphics.getHeight());

		collisionManager.setCollidables();
		collisionManager.detectCollisions();

		// Should be wrapped inside output(?)
		// Render the health bar on top of the player
//		healthBar.render(shape, batch);
//		inventory.render(batch);
	}




	@Override
	public void dispose() {
		batch.dispose();
//		for (TextureObject drop: droplets) {
//			drop.getTexture().dispose();
//		}
//		bucket.getTexture().dispose();
		shape.dispose();
		super.dispose();
	}

}
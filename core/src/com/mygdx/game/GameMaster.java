package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.PlayerControlManager.HealthBar;
import com.mygdx.game.PlayerControlManager.Inventory;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SceneManager.Scene; // Adjust the package name as needed edmund scene
import com.mygdx.game.SceneManager.StartScene; // Adjust the package name as needed edmund scene
import com.badlogic.gdx.utils.ScreenUtils; // edmund scene
import com.mygdx.game.SoundManager.SoundManager; // sound manager

import com.badlogic.gdx.graphics.Color; // background color
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

	public void create() {
		// Initialize SoundManager with background music and sound effect files
		soundManager = new SoundManager("background_music.mp3", "background_music_3.mp3","sound_effect.mp3");

		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Create respective managers
		entityManager = new EntityManager();

		//Initialize IOManager
		ioManager = new IOManager(entityManager,5, soundManager);
		ioManager.setWindowedMode(); // Setting the initial size of the window

		// Create PlayerControlManager and HealthBar instances
		playerControlManager = new PlayerControlManager(entityManager);
		healthBar = new HealthBar(playerControlManager);
		inventory = new Inventory(playerControlManager);

		// Initialize Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, playerControlManager);

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager, ioManager, soundManager);
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

		playerControlManager.createPlayer(10,10);
	}
	// Method to switch to another scene

	public void render() {
		super.render();

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

		shape.begin(ShapeRenderer.ShapeType.Filled);
		// Render the health bar on top of the player
		healthBar.render(shape, batch); // comment out for compile testing - crystal
		inventory.render(batch);
		shape.end();
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
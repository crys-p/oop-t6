package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.CollisionManager.CollisionHandler;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.HealthBar;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.PlayerControlManager.PlayerInputManager;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SceneManager.Scene; // Adjust the package name as needed edmund scene
import com.mygdx.game.SceneManager.StartScene; // Adjust the package name as needed edmund scene
import com.badlogic.gdx.utils.ScreenUtils; // edmund scene
import com.mygdx.game.SoundManager.SoundManager; // sound manager

import com.badlogic.gdx.graphics.Color; // background color
import com.mygdx.game.SimulationManager.SimulationManager;

//public class GameMaster extends ApplicationAdapter
public class GameMaster extends Game {

	private static final int SCREEN_WIDTH = 1280; // done in IO
	private static final int SCREEN_HEIGHT = 720; // done in IO

	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityManager;
	private PlayerInputManager inputManager;
	private SceneManager sceneManager;
	private Scene currentScene; // storing of the current scene reference
	private SimulationManager simulationManager; // Add SimulationManager reference
	private SoundManager soundManager;

	private PlayerControlManager playerControlManager;
	private HealthBar healthBar;
	private CollisionManager collisionManager;
	private CollisionHandler collisionHandler;

	public void create() {
		// Setting the initial size of the window
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT); // done in IO
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Create respective managers
		inputManager = new PlayerInputManager();
		entityManager = new EntityManager();
		// Initialize SoundManager with background music and sound effect files
		soundManager = new SoundManager("background_music.mp3", "background_music_2.mp3","sound_effect.mp3");
		// Initialise Collision Manager for all collision detection and handling
		collisionManager = new CollisionManager(entityManager, soundManager, );

		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager);
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

		// Create PlayerControlManager and HealthBar instances
		playerControlManager = new PlayerControlManager(entityManager);
		healthBar = new HealthBar(playerControlManager);
		playerControlManager.setMaxHealth(100); // Set maximum health
		playerControlManager.setHealth(100); // Set initial health
	}
	// Method to switch to another scene




	public void render() {
		// Clear the screen with the background color of the current scene
		// ScreenUtils.clear(currentScene.getBackgroundColor().r, currentScene.getBackgroundColor().g, currentScene.getBackgroundColor().b, currentScene.getBackgroundColor().a);
		// ScreenUtils.clear(0, 0, 0.2f, 1);

		Scene currentScene = sceneManager.getCurrentScene();
		if (currentScene != null) {
			ScreenUtils.clear(currentScene.getBackgroundColor().r, currentScene.getBackgroundColor().g, currentScene.getBackgroundColor().b, currentScene.getBackgroundColor().a);
		}

		// Play background music
		soundManager.playStartSceneMusic();

		// Check if the current screen is the StartScene
		if (getScreen() instanceof StartScene) {
			// Your StartScreen-specific code here
			System.out.println("Currently on the StartScreen");
		} else {
			// Code for when the screen is not the StartScreen
			System.out.println("Currently not on the StartScreen");
		}

		//entityMgr.setUpMovement();
		entityManager.movement();
		inputManager.setUpInputControl();

		// Keep the player within the screen bounds
//		if(player.getX() > Gdx.graphics.getWidth())
//			player.setX(Gdx.graphics.getWidth());
//		if(player.getY() > Gdx.graphics.getHeight())
//			player.setY(Gdx.graphics.getHeight());

		//ScreenUtils.clear(defaultBackgroundColor.r, defaultBackgroundColor.g, defaultBackgroundColor.b, defaultBackgroundColor.a);
		//ScreenUtils.clear(0, 0, 0.2f, 1);

		// Rendering sprites and shapes
		batch.begin();
		shape.begin(ShapeRenderer.ShapeType.Filled);
		entityManager.drawEntities(batch, shape);
		shape.end();
		batch.end();

		//shermaine
		// Get the player's position
		float playerX = playerControlManager.getPlayerX();
		float playerY = playerControlManager.getPlayerY();

		shape.begin(ShapeRenderer.ShapeType.Filled);
		// Render the health bar on top of the player
		healthBar.render(shape, playerX, playerY);
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
package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityManager.Entity;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerInputManager;
import com.mygdx.game.SceneManager.SceneManager;
import com.mygdx.game.SceneManager.Scene; // Adjust the package name as needed edmund scene
import com.mygdx.game.SceneManager.StartScene; // Adjust the package name as needed edmund scene
import com.badlogic.gdx.utils.ScreenUtils; // edmund scene

import com.badlogic.gdx.graphics.Color; // background color

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

	public void create() {
		// Setting the initial size of the window
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT); // done in IO
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();

		// Create respective managers
		entityManager = new EntityManager();
		inputManager = new PlayerInputManager(); //shermaine

		//sceneManager = new SceneManager(this);
		// Pass the game instance to SceneManager
		sceneManager = new SceneManager((Game) Gdx.app.getApplicationListener(), entityManager);
		sceneManager.showStartScene();

	}
	// Method to switch to another scene


	public void render() {
		// Clear the screen with the background color of the current scene
		//ScreenUtils.clear(currentScene.getBackgroundColor().r, currentScene.getBackgroundColor().g, currentScene.getBackgroundColor().b, currentScene.getBackgroundColor().a);
		//ScreenUtils.clear(0, 0, 0.2f, 1);


		Scene currentScene = sceneManager.getCurrentScene();
		if (currentScene != null) {
			ScreenUtils.clear(currentScene.getBackgroundColor().r, currentScene.getBackgroundColor().g, currentScene.getBackgroundColor().b, currentScene.getBackgroundColor().a);
		}

		// Check if the current screen is the StartScene
		if (getScreen() instanceof StartScene) {
			// Your StartScreen-specific code here
			System.out.println("Currently on the StartScreen");
		} else {
			// Code for when the screen is not the StartScreen
			System.out.println("Currently not on the StartScreen");
		}

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

		entityManager.entityMovement();


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
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
import com.mygdx.game.SoundManager.SoundManager; // sound manager

import com.badlogic.gdx.graphics.Color; // background color
import com.mygdx.game.SimulationManager.SimulationManager;

//public class GameMaster extends ApplicationAdapter
public class GameMaster extends Game {

	private static final int SCREEN_WIDTH = 1280; // done in IO
	private static final int SCREEN_HEIGHT = 720; // done in IO

	private EntityManager entityManager;
	private PlayerInputManager inputManager;
    private Scene currentScene; // storing of the current scene reference
	private SimulationManager simulationManager; // Add SimulationManager reference

	private SceneManager sceneManager;
	private SoundManager soundManager;

	public void create() {
		// Setting the initial size of the window
		Gdx.graphics.setWindowedMode(SCREEN_WIDTH, SCREEN_HEIGHT); // done in IO

		// Create respective managers
		entityManager = new EntityManager();
		inputManager = new PlayerInputManager(); //shermaine

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

	}
	// Method to switch to another scene




	public void render() {

		super.render();

		// Check if the current screen is the StartScene
		if (getScreen() instanceof StartScene) {
			// Your StartScreen-specific code here
			System.out.println("Currently on the StartScreen");
		} else {
			// Code for when the screen is not the StartScreen
			System.out.println("Currently not on the StartScreen");
		}


		entityManager.movement();


		inputManager.setUpInputControl();

		// Keep the player within the screen bounds
//		if(player.getX() > Gdx.graphics.getWidth())
//			player.setX(Gdx.graphics.getWidth());
//		if(player.getY() > Gdx.graphics.getHeight())
//			player.setY(Gdx.graphics.getHeight());
	}




	@Override
	public void dispose() {
//		batch.dispose();
//		for (TextureObject drop: droplets) {
//			drop.getTexture().dispose();
//		}
//		bucket.getTexture().dispose();
//		shape.dispose();

		super.dispose();
	}

}
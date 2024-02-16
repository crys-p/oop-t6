package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.AIControlManager.AI;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.EntityManager.Player;
import com.mygdx.game.PlayerControlManager.PlayerInputManager;

public class GameMaster extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityMgr;
	private Player player;
	private AI ai;
	private PlayerInputManager inputManager;

	public void create() {
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		// Setting up Entities
		entityMgr = new EntityManager();

		// Create object
		player = new Player(-100,-100,20,"player.png", 200,200);
		ai = new AI(0,-100,50,"monster.png",200,200);

		// Create input manager
		inputManager = new PlayerInputManager(); 
	}
	public void render() {
		inputManager.handleInput(player); // Handle player input

		// Update player position
		player.movement(Gdx.graphics.getDeltaTime());
		// Update ai position
		ai.movement(Gdx.graphics.getDeltaTime(), ai.getSpeed());

		// Keep the player within the screen bounds
		if(player.getX() > Gdx.graphics.getWidth())
			player.setX(Gdx.graphics.getWidth());
		if(player.getY() > Gdx.graphics.getHeight())
			player.setY(Gdx.graphics.getHeight());

		ScreenUtils.clear(0, 0, 0.2f, 1);
		// Rendering sprites and shapes
		batch.begin();
//		shape.begin(ShapeRenderer.ShapeType.Filled);
//		entityMgr.drawEntities(batch, shape);
//		shape.end();
		player.draw(batch);
		ai.draw(batch);
		batch.end();

//		entityMgr.entityMovement();
	}
	@Override
	public void dispose() {
		batch.dispose();
//		shape.dispose();
	}
}
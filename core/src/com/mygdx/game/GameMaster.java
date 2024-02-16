package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityManager.Entity;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerInputManager;

public class GameMaster extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityMgr;
	private PlayerInputManager inputManager;

	public void create() {
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		// Setting up Entities
		entityMgr = new EntityManager();
		entityMgr.createEntities();

		// Create input manager
		inputManager = new PlayerInputManager(); //shermaine
	}

	public void render() {
		entityMgr.setUpMovement();
		inputManager.setUpPlayerControl(entityMgr.getPlayers());

		// Keep the player within the screen bounds
//		if(player.getX() > Gdx.graphics.getWidth())
//			player.setX(Gdx.graphics.getWidth());
//		if(player.getY() > Gdx.graphics.getHeight())
//			player.setY(Gdx.graphics.getHeight());

		ScreenUtils.clear(0, 0, 0.2f, 1);
		// Rendering sprites and shapes
		batch.begin();
		shape.begin(ShapeRenderer.ShapeType.Filled);
			entityMgr.drawEntities(batch, shape);
		shape.end();
		batch.end();

		entityMgr.entityMovement();
	}

	@Override
	public void dispose() {
		batch.dispose();
//		for (TextureObject drop: droplets) {
//			drop.getTexture().dispose();
//		}
//		bucket.getTexture().dispose();
		shape.dispose();
	}
}
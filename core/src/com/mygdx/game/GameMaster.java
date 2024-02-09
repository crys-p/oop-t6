package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMaster extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityMgr;

	public void create() {
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		// Setting up Entities
		entityMgr = new EntityManager();
		entityMgr.createEntities();
	}
	
	public void render() {
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

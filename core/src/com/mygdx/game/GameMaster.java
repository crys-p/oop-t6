package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.EntityManager.Player;

import PlayerControlManager.PlayerInputManager;

public class GameMaster extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shape;
	private EntityManager entityMgr;
	
    private Player player;
    private PlayerInputManager inputManager;

	public void create() {
		// Creating renderers
		batch = new SpriteBatch();
		shape = new ShapeRenderer();
		// Setting up Entities
		entityMgr = new EntityManager();
<<<<<<< HEAD
=======
        
        // Create player object
        player = new Player(-100,-100,20,"player.png"); //shermaine
        
        // Create input manager
        inputManager = new PlayerInputManager(); //shermaine
>>>>>>> 5f5ff7dcc6bf196dea6aa877a94914caa4d984e6
	}
	
	public void render() {
        inputManager.handleInput(player); // Handle player input //shermaine 37-54
        
        // Update player position
        player.update(Gdx.graphics.getDeltaTime());
        
        // Keep the player within the screen bounds
        if(player.getX() > Gdx.graphics.getWidth())
            player.setX(Gdx.graphics.getWidth());
        if(player.getY() > Gdx.graphics.getHeight())
        	player.setY(Gdx.graphics.getHeight());
		
		ScreenUtils.clear(0, 0, 0.2f, 1);
		// Rendering sprites and shapes
		batch.begin();
//		shape.begin(ShapeRenderer.ShapeType.Filled);
//			entityMgr.drawEntities(batch, shape);
//		shape.end();
		player.draw(batch); //shermaine
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

package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
//import com.mygdx.game.EntityManager.Entity;
import com.mygdx.game.EntityManager.Player;

public class PlayerInputManager {
	private boolean isJumping = false; // Flag to track if the entity is currently jumping

    public void handleInput(Player player) {
    	
        // Move entity based on input
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.moveLeft(10);
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.moveRight(10);
        if(Gdx.input.isKeyPressed(Input.Keys.UP))
            player.moveUp(10);
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN))
            player.moveDown(10);
        
        // Jump action
        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isJumping) { // Check if space key is pressed and player is not already jumping
            player.jump();
            isJumping = true; // Set jumping flag
        }

        // Reset jumping flag if space key is released
        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            isJumping = false;
        }
    }
    public void resetJumpingFlag() {
        isJumping = false;
    }
}


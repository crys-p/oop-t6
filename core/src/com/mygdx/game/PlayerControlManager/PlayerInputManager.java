package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.EntityManager.Player;

import java.util.List;

public class PlayerInputManager {
//	private boolean isJumping = false; // Flag to track if the entity is currently jumping

    public void setUpPlayerControl(List<Player> players) {
        for (Player player: players) {
            // Move entity based on input
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                player.moveLeft(20);
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                player.moveRight(20);
            if (Gdx.input.isKeyPressed(Input.Keys.UP))
                player.moveUp(20);
            if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
                player.moveDown(20);
        }
//        // Jump action
//        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && !isJumping) { // Check if space key is pressed and entity is not already jumping
//            entity.jump();
//            isJumping = true; // Set jumping flag
//        }
        
//        // Reset jumping flag if space key is released
//        if(!Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
//            isJumping = false;
//        }
    }
    
//    public void resetJumpingFlag() {
//        isJumping = false;
//    }
}


package com.mygdx.game.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Config;
import com.mygdx.game.EntityManager.EntityManager;

public class AIControlManager {
    private boolean movingRight = true;
    public float moveRandomly(float entityX) {
        // Calculate movement based on direction
        float movement = movingRight ? 20 : -20; // if movingRight = true, entity moves to the right; if movingRight = false, entity moves to the left

        // Update position based on movement
        float newX = entityX + movement;

        // Check if AI has reached the edge of the screen
        if (newX < 0) {
            newX = 0; // Reset position to prevent going off-screen
            movingRight = true; // Change direction to right
        } else if (newX > (float) Gdx.graphics.getWidth() / 2) {
            newX = (float) Gdx.graphics.getWidth() / 2; // Reset position
            movingRight = false; // Change direction to left
        }

        System.out.println("New X Position: " +newX);

        // Return the new X position
        return newX;
    }
    public void chasePlayer() {}

}

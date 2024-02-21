package com.mygdx.game.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Config;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.Objects;

public class AIControlManager {
    private boolean movingRight = true;
    private boolean movingDown = true;
    public float moveRandomly(float entityPosition, String movementCase) {
        float newX = 0;
        float newY = 0;
        switch (movementCase) {
            case "LRmovement":
                // Calculate movement based on direction
                float LRmovement = movingRight ? 20 : -20; // if movingRight = true, entity moves to the right; if movingRight = false, entity moves to the left

                // Update position based on movement
                newX = entityPosition + LRmovement;

                // Check if AI has reached the edge of the screen
                if (newX < 0) {
                    newX = 0; // Reset position to prevent going off-screen
                    movingRight = true; // Change direction to right
                } else if (newX > (float) Gdx.graphics.getWidth() / 2) {
                    newX = (float) Gdx.graphics.getWidth() / 2; // Reset position
                    movingRight = false; // Change direction to left
                }
                System.out.println("New X Position: " + newX);
            break;
            case "UDmovement":
                // Calculate movement based on direction
                float UDmovement = movingDown ? 20 : -20; // if movingDown = true, entity moves downwards; if movingDown = false, entity moves upwards

                // Update position based on movement
                newY = entityPosition + UDmovement;

                // Check if AI has reached the edge of the screen
                if (newY < 0) {
                    newY = 0; // Reset position to prevent going off-screen
                    movingDown = true; // Change direction to downwards
                } else if (newY > (float) Gdx.graphics.getHeight() / 2) {
                    newY = (float) Gdx.graphics.getHeight() / 2; // Reset position
                    movingDown = false; // Change direction to upwards
                }
                System.out.println("New X Position: " + newY);
            break;
            case "ChasePlayer":
                break;
            default:
                throw new IllegalArgumentException("Invalid movementCase: " + movementCase);
        }
//        // Calculate movement based on direction
//        float movement = movingRight ? 20 : -20; // if movingRight = true, entity moves to the right; if movingRight = false, entity moves to the left
//
//        // Update position based on movement
//        float newX = entityX + movement;
//
//        // Check if AI has reached the edge of the screen
//        if (newX < 0) {
//            newX = 0; // Reset position to prevent going off-screen
//            movingRight = true; // Change direction to right
//        } else if (newX > (float) Gdx.graphics.getWidth() / 2) {
//            newX = (float) Gdx.graphics.getWidth() / 2; // Reset position
//            movingRight = false; // Change direction to left
//        }
//
//        System.out.println("New X Position: " +newX);
//
        // Return newX if "LRmovement" or newY if "UDmovement"
        if (Objects.equals(movementCase, "LRmovement")) {
            return newX;
        }
        return newY;
    }
    public void chasePlayer() {}

}

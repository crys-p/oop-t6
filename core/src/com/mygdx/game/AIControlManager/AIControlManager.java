package com.mygdx.game.AIControlManager;

import com.badlogic.gdx.Gdx;

import java.util.Random;

public class AIControlManager {
    private boolean movingRight = true;
    private boolean movingDown = true;
    private float acceleration; // Adjust the acceleration value as needed
    private float deceleration; // Adjust the deceleration value as needed
    private Random random = new Random(); // Create a Random object to generate random values
    public AIControlManager() {
        this.acceleration = random.nextFloat() * 0.01f; // Generate a random acceleration between 0 and 5
        this.deceleration = random.nextFloat() * 5.0f; // Generate a random deceleration between 0 and 2
    }
    public float moveLR(float entityX) {
        float movementSpeed = 5; // Initial movement speed
        float direction = 1; // Initial direction: right

        // Update position based on movement speed and direction
        float newX = entityX + (movementSpeed * direction);

        // Check if AI has reached the edge of the screen
        if (newX <= 0 || newX >= (float) Gdx.graphics.getWidth()) {
            direction *= -1; // Reverse the direction
            newX = Math.max(0, Math.min((float) Gdx.graphics.getWidth(), newX)) * direction; // Keep AI within screen bounds
        }

        // Debug statement to print the new X position and updated speed
        System.out.println("New X Position: " + newX);

        // Return the new X position
        return newX;
    }

    public float moveUD(float entityY) {
        float movementSpeed = 5; // Initial movement speed
        float direction = 1; // Initial direction: up

        // Update position based on movement speed and direction
        float newY = entityY + (movementSpeed * direction);

        // Check if AI has reached the edge of the screen
        if (newY <= 0 || newY >= (float) Gdx.graphics.getHeight()) {
            // Reverse the direction if the edge is reached
            direction *= -1;

            // Adjust the position to keep the AI within screen bounds
            newY = Math.max(0, Math.min((float) Gdx.graphics.getHeight(), newY)) * direction;
        }

        // Debug statement to print the new Y position and updated speed
        System.out.println("New Y Position: " + newY);

        // Return the new Y position
        return newY;
    }


//    public float moveLR(float entityX, float currentSpeed) {
//        // Calculate acceleration
//        float accelerationX = random.nextFloat() * (2 * acceleration) - acceleration;
//
//        // Calculate new speed by adding acceleration
//        float newSpeed = currentSpeed + accelerationX;
//
//        // Apply deceleration only if the AI hits the screen edge and needs to reverse its direction
//        if ((newSpeed > 0 && !movingRight && entityX <= 0) || (newSpeed < 0 && movingRight && entityX >= Gdx.graphics.getWidth())) {
//            newSpeed -= Math.min(Math.abs(newSpeed), deceleration) * Math.signum(newSpeed);
//        }
//
//        // Ensure the speed is always positive
//        newSpeed = Math.max(5, newSpeed); // Minimum speed of 5
//
//        // Calculate movement based on direction
//        float movement = movingRight ? newSpeed : -newSpeed; // Adjust the movement speed as needed
//
//        // Update position based on movement
//        float newX = entityX + movement;
//
//        // Debug statement to print the new X position and updated speed
//        System.out.println("New X Position: " + newX + ", Speed: " + newSpeed);
//
//        // Check if AI has reached the edge of the screen
//        if (newX <= 0) {
//            newX = 0; // Reset position to prevent going off-screen
//            movingRight = true; // Change direction to right
//        } else if (newX >= (float) Gdx.graphics.getWidth()) {
//            newX = (float) Gdx.graphics.getWidth(); // Reset position
//            movingRight = false; // Change direction to left
//        }
//
//        // Return the new X position
//        return newX;
//    }

//    public float moveUD(float entityY, float currentSpeed) {
//        // Calculate acceleration
//        float accelerationY = random.nextFloat() * (2 * acceleration) - acceleration;
//
//        // Calculate new speed by adding acceleration
//        float newSpeed = currentSpeed + accelerationY;
//
//        // Apply deceleration only if the AI hits the screen edge and needs to reverse its direction
//        if ((newSpeed > 0 && !movingDown && entityY <= 0) || (newSpeed < 0 && movingDown && entityY >= Gdx.graphics.getHeight())) {
//            newSpeed -= Math.min(Math.abs(newSpeed), deceleration) * Math.signum(newSpeed);
//        }
//
//        // Ensure the speed is always positive
//        newSpeed = Math.max(5, newSpeed); // Minimum speed of 5
//
//        // Calculate movement based on direction
//        float movement = movingDown ? newSpeed : -newSpeed; // Adjust the movement speed as needed
//
//
//        // Update position based on movement
//        float newY = entityY + movement;
//
//        // Debug statement to print the new Y position and updated speed
//        System.out.println("New Y Position: " + newY + ", Speed: " + newSpeed);
//
//        // Check if AI has reached the edge of the screen
//        if (newY <= 0) {
//            newY = 0; // Reset position to prevent going off-screen
//            movingDown = true; // Change direction to down
//        } else if (newY >= (float) Gdx.graphics.getHeight()) {
//            newY = (float) Gdx.graphics.getHeight(); // Reset position
//            movingDown = false; // Change direction to up
//        }
//
//        // Return the new Y position
//        return newY;
//    }

    public void chasePlayer() {}

//    public float moveRandomly(float entityPosition, String movementCase) {
//        float newX = 0;
//        float newY = 0;
//        switch (movementCase) {
//            case "LRmovement":
//                // Calculate movement based on direction
//                float LRmovement = movingRight ? 20 : -20; // if movingRight = true, entity moves to the right; if movingRight = false, entity moves to the left
//
//                // Update position based on movement
//                newX = entityPosition + LRmovement;
//
//                // Check if AI has reached the edge of the screen
//                if (newX < 0) {
//                    newX = 0; // Reset position to prevent going off-screen
//                    movingRight = true; // Change direction to right
//                } else if (newX > (float) Gdx.graphics.getWidth()) {
//                    newX = (float) Gdx.graphics.getWidth(); // Reset position
//                    movingRight = false; // Change direction to left
//                }
//                System.out.println("New X Position: " + newX);
//            break;
//            case "UDmovement":
//                // Calculate movement based on direction
//                float UDmovement = movingDown ? 20 : -20; // if movingDown = true, entity moves downwards; if movingDown = false, entity moves upwards
//
//                // Update position based on movement
//                newY = entityPosition + UDmovement;
//
//                // Check if AI has reached the edge of the screen
//                if (newY < 0) {
//                    newY = 0; // Reset position to prevent going off-screen
//                    movingDown = true; // Change direction to downwards
//                } else if (newY > (float) Gdx.graphics.getHeight()) {
//                    newY = (float) Gdx.graphics.getHeight(); // Reset position
//                    movingDown = false; // Change direction to upwards
//                }
//                System.out.println("New X Position: " + newY);
//            break;
//            case "ChasePlayer":
//                break;
//            default:
//                throw new IllegalArgumentException("Invalid movementCase: " + movementCase);
//        }
//        // Return newX if "LRmovement" or newY if "UDmovement"
//        if (Objects.equals(movementCase, "LRmovement")) {
//            return newX;
//        }
//        return newY;
//    }
}
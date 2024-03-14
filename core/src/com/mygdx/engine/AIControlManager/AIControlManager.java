package com.mygdx.engine.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.SimulationManager.SimulationManager;

import java.util.Random;

public class AIControlManager {



    private static final Random random = new Random();
    private SimulationManager simulationManager;
    public AIControlManager(){
        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("AIControlManager initialized"); // Log initialization message
    }
    public static float[] moveLR(float entityX, float entityY) {
        float movementSpeed = 5; // Initial movement speed
        float direction = 1; // Initial direction: right

        // Update position based on movement speed and direction
        float newX = entityX + (movementSpeed * direction);

        // Check if AI has reached the edge of the screen
        if (newX <= 0 || newX >= (float) Gdx.graphics.getWidth()) {
            // Reverse the direction
            direction *= -1;
            // Randomize Y position
            entityY = random.nextFloat() * (Gdx.graphics.getHeight() - 100);
            // Adjust the position to keep the AI within screen bounds
            newX = Math.max(0, Math.min((float) Gdx.graphics.getWidth(), newX)) * direction;
        }

        // Return the new position
        return new float[] {newX, entityY};
    }

    public static float[] moveUD(float entityX, float entityY) {
        float movementSpeed = 5; // Initial movement speed
        float direction = 1; // Initial direction: up

        // Update position based on movement speed and direction
        float newY = entityY + (movementSpeed * direction);

        // Check if AI has reached the edge of the screen
        if (newY <= 0 || newY >= (float) Gdx.graphics.getHeight()) {
            // Reverse the direction if the edge is reached
            direction *= -1;
            // Randomize X position
            entityX = random.nextFloat() * (Gdx.graphics.getWidth() - 100);
            // Adjust the position to keep the AI within screen bounds
            newY = Math.max(0, Math.min((float) Gdx.graphics.getHeight(), newY)) * direction;
        }

        // Return the new position
        return new float[] {entityX, newY};
    }

}
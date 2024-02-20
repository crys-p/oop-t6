package com.mygdx.game.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Config;

public class AIControl {
    private float x;
    private float y;
    private static final float DEFAULT_INITIAL_X = 100.0f;
    private static final float DEFAULT_INITIAL_Y = 200.0f;

    public AIControl(float initialX, float initialY) {
        this.x = initialX;
        this.y = initialY;
    }
    public void aiMovement() {
        // Generate random values for movement in x and y directions
        float moveX = (float) (Math.random() * 10 - 5); // Random value between -5 and 5
        float moveY = (float) (Math.random() * 10 - 5); // Random value between -5 and 5

        int screenWidth = Config.SCREEN_WIDTH;
        int screenHeight = Config.SCREEN_HEIGHT;

        // Update AI position by adding the movement values
        x += moveX;
        y += moveY;

        // Ensure the AI stays within the game world boundaries
        // Example: Check if AI goes beyond screen bounds and adjust its position if necessary
        // You should replace the screenWidth and screenHeight with appropriate values
        if (x < 0) {
            x = 0;
        } else if (x > screenWidth) {
            x = screenWidth;
        }
        if (y < 0) {
            y = 0;
        } else if (y > screenHeight) {
            y = screenHeight;
        }
    }
    public void aiInitialise() {
        // Set initial position of the AI
        x = DEFAULT_INITIAL_X;
        y = DEFAULT_INITIAL_Y;
    }
}

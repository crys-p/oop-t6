package com.mygdx.game.PlayerMovementStrategy;

import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;
import static com.mygdx.engine.SceneManager.SceneManager.SCENE_WIDTH;

public class Player1Movement extends PlayerMovement {

    @Override
    public float[] calculateMovement(float x, float y, float speed) {
        if (playerInstructions == PlayerInstructions.UP) {
            return moveUp(x, y, speed);
        }
        if (playerInstructions == PlayerInstructions.DOWN) {
            return moveDown(x, y, speed);
        }
        if (playerInstructions == PlayerInstructions.LEFT) {
            return moveLeft(x, y, speed);
        }
        if (playerInstructions == PlayerInstructions.RIGHT) {
            return moveRight(x,y, speed);
        }

        return new float[] {x,y};

        // Add more depending on instructions
    }

    private float[] moveUp(float x, float y, float speed) {
        if (y > SCENE_HEIGHT - 40) return new float[] {x,y};
        y += speed;
        return new float[] {x, y};
    }

    private float[] moveDown(float x, float y, float speed) {
        if (y <= 0) return new float[] {x,y};
        y -= speed;
        return new float[] {x, y};
    }

    private float[] moveLeft(float x, float y, float speed) {
        if (x <= 0) return new float[] {x,y};
        x -= speed;
        return new float[] {x, y};
    }

    private float[] moveRight(float x, float y, float speed) {
        if (x > SCENE_WIDTH - 40) return new float[] {x,y};
        x += speed;
        return new float[] {x, y};
    }

}

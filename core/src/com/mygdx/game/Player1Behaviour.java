package com.mygdx.game;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.Movement.PlayerBehaviour;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class Player1Behaviour extends PlayerBehaviour {
    @Override
    public float[] calculateMovement(float x, float y, float speed) {
        return new float[] {x,y};
    }

    @Override
    public float[] calculateMovement(PlayerInstructions instructions, float x, float y, float speed) {
        if (instructions == PlayerInstructions.UP) {
            return moveUp(x, y, speed);
        }
        if (instructions == PlayerInstructions.DOWN) {
            return moveDown(x, y, speed);
        }
        if (instructions == PlayerInstructions.LEFT) {
            return moveLeft(x, y, speed);
        }
        if (instructions == PlayerInstructions.RIGHT) {
            return moveRight(x,y, speed);
        }
        return new float[] {x,y};

        // Add more depending on instructions
    }

    private float[] moveUp(float x, float y, float speed) {
        if (y > SCREEN_HEIGHT) return new float[] {x,y};
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
        if (x > SCREEN_WIDTH) return new float[] {x,y};
        x += speed;
        return new float[] {x, y};
    }

}

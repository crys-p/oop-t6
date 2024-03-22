package com.mygdx.game.GameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class FastFood extends Enemy {
    public FastFood(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

    public FastFood(float x, float y, float speed, Texture texture, AIMovement movement, float gamePoints) {
        super(x, y, speed, texture, movement);
        this.setDamage(-1 * gamePoints);
    }
}

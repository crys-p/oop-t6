package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class FastFood extends Enemy {
    public FastFood(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }
}

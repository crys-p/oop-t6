package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class Vegetable extends Collectible {

    public Vegetable(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

}

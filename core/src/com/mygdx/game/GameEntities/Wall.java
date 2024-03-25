package com.mygdx.game.GameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class Wall extends Enemy {
    public Wall(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
        this.boundingBox = new Rectangle(x, y, this.getWidth(), this.getHeight());
    }

}

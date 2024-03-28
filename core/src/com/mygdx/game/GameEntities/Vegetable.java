package com.mygdx.game.GameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class Vegetable extends Collectible {

    private EntityType myType;

    public Vegetable(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

    public Vegetable(float x, float y, float speed, Texture texture, AIMovement movement, EntityType type, int gameValue) {
        super(x, y, speed, texture, movement);
        this.setPoints(gameValue);
        this.myType = type;
    }


    public EntityType getMyType() {
        return myType;
    }

}

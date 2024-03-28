package com.mygdx.game.GameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public class Fruit extends Collectible {
    private EntityType myType;
    private boolean isRecovery = false;

    public Fruit(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

    public Fruit(float x, float y, float speed, Texture texture, AIMovement movement, EntityType type, int gameValue) {
        super(x, y, speed, texture, movement);
        this.setPoints(gameValue);
        this.myType = type;
        if (this.myType == EntityType.BIGAPPLE) {
            this.isRecovery = true;
        }
    }

    public boolean isRecovery() {
        return this.isRecovery;
    }
}

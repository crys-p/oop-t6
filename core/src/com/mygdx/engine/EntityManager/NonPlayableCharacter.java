package com.mygdx.engine.EntityManager;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;

public abstract class NonPlayableCharacter extends Entity {

    protected AIMovement movement;

    public NonPlayableCharacter(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture);
        this.movement = movement;
    }

    @Override
    protected void movement() {
        // Call movement method to get the new positions
        float[] newPosition = movement.calculateMovement(this.getX(), this.getY(), this.getSpeed()); // Pass entity speed if needed
        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

    protected void logConsole() {
        System.out.printf("I am NPC at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }
}

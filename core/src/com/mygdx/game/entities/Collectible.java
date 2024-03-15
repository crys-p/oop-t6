package com.mygdx.game.entities;


import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.EntityManager.Entity;

public class Collectible extends Entity {

    private AIMovement movement;
    public Collectible(float x, float y, String image, AIMovement movement) {
        super(x, y, image);
        this.movement = movement;
    }


    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        // Call moveLR to get the new positions
        float[] newPosition = movement.moveUD(this.getX(), this.getY());

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

}

package com.mygdx.game.entities;


import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;

public class Collectible extends Entity implements iCollidable {

    public Collectible(float x, float y, String image) {
        super(x, y, image);
    }


    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        // Call moveLR to get the new positions
        float[] newPosition = AIControlManager.moveUD(this.getX(), this.getY());

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

    @Override
    public Rectangle getBoundingBox() {
        return super.getBoundingBox();
    }
}

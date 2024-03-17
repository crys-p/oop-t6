package com.mygdx.game.entities;


import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;

public class Collectible extends Entity implements iCollidable {

    private AIMovement movement;

    public Collectible(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture);
        this.movement = movement;
    }


    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

    @Override
    protected void movement() {
        // Assuming you want to move left-right, you can get an instance of LRMovement
        AIMovement udMovement = AIControlManager.getUDMovement();

        // Call moveLR to get the new positions
        float[] newPosition = udMovement.calculateMovement(this.getX(), this.getY(), this.getSpeed()); // Pass entity speed if needed

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }
}

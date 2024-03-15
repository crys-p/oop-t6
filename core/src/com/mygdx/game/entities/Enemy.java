package com.mygdx.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;

public class Enemy extends Entity implements iCollidable {
    private float damage = 10; // default damage is 10
    private AIMovement movement;

    public Enemy(float x, float y, String image, AIMovement movement) {
        super(x, y, image);
        this.movement = movement;
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float dmg) {
        this.damage = dmg;
    }

    @Override
    protected void logConsole() {
        System.out.printf("In an Enemy at %f,%f position. My EntityID is %d\n",this.getX(), this.getY(), this.getEntityID());
    }

    @Override
    protected void movement() {
        // Call moveLR to get the new positions
        float[] newPosition = movement.moveLR(this.getX(), this.getY());

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

}

package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;

public class Enemy extends Entity implements iCollidable {
    private float damage = 10; // default damage is 10
    private AIMovement movement;

    public Enemy(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture);
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
        // Assuming you want to move left-right, you can get an instance of LRMovement
        AIMovement lrMovement = AIControlManager.getLRMovement();

        // Call moveLR to get the new positions
        float[] newPosition = lrMovement.calculateMovement(this.getX(), this.getY(), this.getSpeed()); // Pass entity speed if needed

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }


}

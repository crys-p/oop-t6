package com.mygdx.game.entities;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;

public class Enemy extends Entity implements iCollidable {
    private float damage = 10; // default damage is 10

    public Enemy(float x, float y, String image) {
        super(x, y, image);
    }

    public float getDamage() {
        return this.damage;
    }

    public void setDamage(float dmg) {
        this.damage = dmg;
    }

    @Override
    protected void logConsole() {
        System.out.printf("In an Enemy at %f,%f position. My EntityID is %d\n",this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        // Call moveLR to get the new positions
        float[] newPosition = AIControlManager.moveLR(this.getX(), this.getY());

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

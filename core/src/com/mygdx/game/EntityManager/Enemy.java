package com.mygdx.game.EntityManager;

import com.mygdx.game.AIControlManager.AIControlManager;

class Enemy extends Entity {
    private float damage = 10; // default damage is 10

    protected Enemy(float x, float y, String image) {
        super(x, y, image);
    }

    protected float getDamage() {
        return this.damage;
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

}

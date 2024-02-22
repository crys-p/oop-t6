package com.mygdx.game.EntityManager;


import com.mygdx.game.AIControlManager.AIControlled;

class Enemy extends Entity implements AIControlled {
    protected float damage = 5; // default damage is 5

    Enemy(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY, image);
    }

    protected void setDamage(float dmg) {
        this.damage = dmg;
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
        float[] newPosition = aiControlManager.moveLR(this.getX(), this.getY());

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

}

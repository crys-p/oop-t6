package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AIControlManager.AIControlled;

class Collectible extends Entity implements AIControlled {

    protected Collectible(float x, float y, String image) {
        super(x, y, image);
    }


    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        // Call moveLR to get the new positions
        float[] newPosition = aiControlManager.moveUD(this.getX(), this.getY());

        // Update the X position
        this.setX(newPosition[0]);
        // Update the Y position
        this.setY(newPosition[1]);
        updateBoundingBox();
    }

}

package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.AIControlManager.AIControlManager;

public class Collectible extends Entity implements AIControlled {

    protected Collectible(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY, image);
    }


    protected void draw(ShapeRenderer shape) {
//        shape.rect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        float newY = aiControlManager.moveUD(this.getY());
        this.setY(newY);
        updateBoundingBox();
    }

}

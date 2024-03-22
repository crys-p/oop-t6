package com.mygdx.game.GameEntities;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.EntityManager.NonPlayableCharacter;
import com.mygdx.engine.EntityManager.iCollidable;

public abstract class Collectible extends NonPlayableCharacter implements iCollidable {

    private float points = 10; // default points is 10

    public Collectible(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

    protected void logConsole() {
        System.out.printf("I am item at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

    public float getPoints() {
        return this.points;
    }

    public void setPoints(float dmg) {
        this.points = dmg;
    }


    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
}

package com.mygdx.game.GameEntities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.EntityManager.NonPlayableCharacter;
import com.mygdx.engine.CollisionManager.iCollidable;

public abstract class Enemy extends NonPlayableCharacter implements iCollidable {
    private float damage = 0; // default damage is 0

    public Enemy(float x, float y, float speed, Texture texture, AIMovement movement) {
        super(x, y, speed, texture, movement);
    }

    public float getDamage() {
        return this.damage;
    }

    protected void setDamage(float dmg) {
        this.damage = dmg;
    }

    @Override
    protected void logConsole() {
        System.out.printf("In an Enemy at %f,%f position. My EntityID is %d\n",this.getX(), this.getY(), this.getEntityID());
    }

    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }

    public void respawn(float x, float y) {
        this.setX(x);
        this.setY(y);
    }
}

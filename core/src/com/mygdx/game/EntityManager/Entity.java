package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

abstract class Entity {
    protected int entityID;
    protected float x;
    protected float y;
    protected float velocityX; // Horizontal velocity
    protected float velocityY; // Vertical velocity
    protected Rectangle boundingBox;

    // Default constructor
    protected Entity(float x, float y, float speed) {
        this.x = 0;
        this.y = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Constructor for entities that start at fixed positions
    protected Entity(float x, float y, float velocityX, float velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    protected float getX() {
        return this.x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    //shermaine
    // Public accessor method
    public float getEntityX() {
        return getX();
    }
    public float getEntityY() {
        return getY();
    }

    protected float getY() {
        return this.y;
    }

    protected void setY(float y) {
        this.y = y;
    }

    protected void moveUp(float value) {
        this.y += value;
    }

    protected void moveDown(float value) {
        this.y -= value;
    }

    protected void moveLeft(float value) {
        this.x -= value;
    }

    protected void moveRight(float value) {
        this.x += value;
    }

    protected void setEntityID(int id) {
        this.entityID = id;
    }
    protected void jump() { //shermaine
        velocityY = 500; // Adjust jump velocity as needed
    }

    protected float getVelocityX() {
        return this.velocityX;
    }

    protected float getVelocityY() {
        return this.velocityY;
    }

    protected void incrementVelocityX(float value) {
        this.velocityX += value;
    }

    protected void setVelocityX(float value) {
        this.velocityX = value;
    }

    protected void setVelocityY(float value) {
        this.velocityY = value;
    }

    protected  abstract float getHeight();
    protected abstract float getWidth();

    protected void draw(SpriteBatch batch) {
        // Method override for Sprites
    }

    protected void draw(ShapeRenderer shape) {
        // Method override for Shapes
    }

    protected abstract void logConsole();

    protected abstract void movement();

    protected void inputMovement(int keyMovement) {
    }
}

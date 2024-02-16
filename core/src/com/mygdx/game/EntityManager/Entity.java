package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public abstract class Entity {

    protected float x;
    protected float y;
    protected float velocityX; // Horizontal velocity
    protected float velocityY; // Vertical velocity

    // Default constructor
    public Entity(float x, float y, float speed) {
        this.x = 0;
        this.y = 0;
        this.velocityX = 0;
        this.velocityY = 0;
    }

    // Constructor for entities that start at random places
    public Entity(Random random) {
        this.x = random.nextFloat();
        this.y = 450;
        this.velocityX = random.nextFloat();
        this.velocityY = random.nextFloat();
    };

    // Constructor for entities that start at fixed positions
    public Entity(float x, float y, float velocityX, float velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void moveUp(float value) {
        this.y += value;
    }

    public void moveDown(float value) {
        this.y -= value;
    }

    public void moveLeft(float value) {
        this.x -= value;
    }

    public void moveRight(float value) {
        this.x += value;
    }
    
    public void jump() { //shermaine
        velocityY = 500; // Adjust jump velocity as needed
    }

    public float getVelocityX() {
        return this.velocityX;
    }

    public float getVelocityY() {
        return this.velocityY;
    }

    public void incrementVelocityX(float value) {
        this.velocityX += value;
    }

    public void setVelocityX(float value) {
        this.velocityX = value;
    }

    public void setVelocityY(float value) {
        this.velocityY = value;
    }

    public void draw(SpriteBatch batch) {
        // Method override for Sprites
    }

    public void draw(ShapeRenderer shape) {
        // Method override for Shapes
    }

    public abstract void logConsole();

    public abstract void movement(float deltaTime);
}

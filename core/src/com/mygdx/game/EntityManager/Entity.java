package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public abstract class Entity implements iMovable {
    private float x;
    private float y;
    private float speed;

    // Default constructor
    public Entity() {
        this.x = 0;
        this.y = 0;
        this.speed = 0;
    }

    // Constructor for entities that start at random places
    public Entity(Random random) {
        this.x = random.nextFloat(700);
        this.y = 450;
        this.speed = random.nextFloat(1, 8);
        System.out.println("Speed = " + this.speed);
    };

    // Constructor for entities with fixed position
    public Entity(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
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

    public void decrementY(float value) {
        this.y -= value;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void incrementSpeed(float value) {
        this.speed += value;
    }

    public void draw(SpriteBatch batch) {
        // Method override for Sprites
    }

    public void draw(ShapeRenderer shape) {
        // Method override for Shapes
    }

    public abstract void update();

}

package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class Entity {
    private Texture texture;
    private int entityID;
    private float x;
    private float y;
    private float speed;
    protected Rectangle boundingBox;

    // Constructor for entities that start at fixed positions
    protected Entity(float x, float y, float speed, Texture texture) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.texture = texture;
        this.boundingBox = new Rectangle(this.x, this.y, this.getWidth() * 3/4, this.getHeight() * 3/4);
    }

    protected float getX() {
        return this.x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected float getY() {
        return this.y;
    }

    protected void setY(float y) {
        this.y = y;
    }

    protected float getSpeed() {
        return this.speed;
    }

    protected void incrementSpeed(float value) {
        this.speed += value;
    }

    protected void setSpeed(float value) {
        this.speed = value;
    }
    protected int getEntityID() {
        return this.entityID;
    }

    protected void setEntityID(int id) {
        this.entityID = id;
    }

    protected void updateBoundingBox() {
        this.boundingBox.setX(this.x);
        this.boundingBox.setY(this.y);
    }

    protected float getHeight() {
        return this.texture.getHeight();
    }

    protected float getWidth() {
        return this.texture.getWidth();
    }

    protected void draw(SpriteBatch batch) {
        // Method that can be overridden for Sprites
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    protected abstract void logConsole();

    protected abstract void movement();

    protected void setPlayerInstructions(PlayerInstructions instructions) {};

    protected Texture getTexture() {
        return this.texture;
    }

    protected void setTexture(Texture texture) {
        this.texture = texture;
    }
}

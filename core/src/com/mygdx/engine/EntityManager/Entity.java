package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class Entity {
    protected Texture texture;
    protected int entityID;
    protected float x;
    protected float y;
    private Rectangle boundingBox;

    // Constructor for entities that start at fixed positions
    protected Entity(float x, float y, String image) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(Gdx.files.internal(image));
        boundingBox = new Rectangle(this.x, this.y, this.getWidth() * 3/4, this.getHeight() * 3/4 );
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

    protected void inputMovement(PlayerInstructions instructions) {}

    // Im not sure what to do here yet haha
    protected void aiMovement() {}

    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
}

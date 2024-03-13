package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.game.PlayerControlConfigs;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class Character extends Entity implements iCollidable {
    private float speed;

    public Character(float x, float y, float speed, String image) {
        super(x, y, image);
        this.speed = speed;
    }

    protected void movement() {
        // Prevent player from moving beyond screen bounds
        if (x < 0) {
            x = 0;
        }
        if (x > SCREEN_WIDTH - this.getWidth()) {
            x = SCREEN_WIDTH - this.getWidth();
        }
        if (y < 0) {
            y = 0;
        }
        if (y > SCREEN_HEIGHT - this.getHeight()) {
            y = SCREEN_HEIGHT - this.getHeight();
        }
        updateBoundingBox();
    }

    protected void inputMovement(PlayerInstructions instructions) {
        float movementAmount = this.getSpeed() * Gdx.graphics.getDeltaTime();
        switch(instructions) {
            case LEFT:
                this.moveLeft(movementAmount);
                break;
            case RIGHT:
                this.moveRight(movementAmount);
                break;
            case UP:
                this.moveUp(movementAmount);
                break;
            case DOWN:
                this.moveDown(movementAmount);
        }
        updateBoundingBox();
    }

    protected void logConsole() {
        System.out.printf("I am a character at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
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

    @Override
    public Rectangle getBoundingBox() {
        return super.getBoundingBox();
    }
}

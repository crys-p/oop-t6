package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.PlayerControlManager.PlayerInstructions;

import static com.mygdx.game.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.game.IOManager.IOManager.SCREEN_WIDTH;

class Character extends Entity {
    private float speed;
    private String inputControls;

    protected Character(float x, float y, float speed, String image, String controls) {
        super(x, y, image);
        this.inputControls = controls;
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

    public String getInputControls() {
        return inputControls;
    }

    public void setInputControls(String inputControls) {
        this.inputControls = inputControls;
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
}

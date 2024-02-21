package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.PlayerControlManager.PlayerInstructions;

class Character extends Entity {
    protected String inputControls;

    protected Character(float x, float y, float velocityX, float velocityY, String image, String controls) {
        super(x, y, velocityX, velocityY, image);
        this.inputControls = controls;
    }

    protected void draw(ShapeRenderer shape) {
//        shape.rect(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height);
    }

    // can this movement code combine with input movement?
    protected void movement() { //shermaine
//        float deltaTime = Gdx.graphics.getDeltaTime();
//        // Update player's position based on velocity
//        this.y += velocityY * deltaTime;
//
//        // Simulate gravity
//        velocityY -= 500 * deltaTime; // Adjust gravity as needed

        // Prevent player from falling through the ground
        if (y < 0) {
            y = 0;
            velocityY = 0;
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


    protected void setInputControls(String control) {
        this.inputControls = control;
    }

    protected void logConsole() {
        System.out.printf("I am a character at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }
}

package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Character extends Entity {
    private final Texture texture;
    protected String inputControls;

    protected Character(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY);
        this.texture = new Texture(Gdx.files.internal(image));
    }

    @Override
    public float getHeight() {
        return this.texture.getHeight();
    }

    @Override
    public float getWidth() {
        return this.texture.getWidth();
    }

    protected void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    // can this movement code combine with input movement?
    protected void movement() { //shermaine
        float deltaTime = Gdx.graphics.getDeltaTime();
        // Update player's position based on velocity
        this.y += velocityY * deltaTime;

        // Simulate gravity
        velocityY -= 1000 * deltaTime; // Adjust gravity as needed

        // Prevent player from falling through the ground
        if (y < 0) {
            y = 0;
            velocityY = 0;
        }
    }

    protected void inputMovement(int key) {
        switch(key) {
            case Input.Keys.LEFT:
                this.moveLeft(20);
                break;
            case Input.Keys.RIGHT:
                this.moveRight(20);
                break;
            case Input.Keys.UP:
                this.moveUp(20);
                break;
            case Input.Keys.DOWN:
                this.moveDown(20);
        }
        updateBoundingBox();
    }

    protected void hardcodeMovementListener() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.moveLeft(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.moveRight(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.moveUp(20);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.moveDown(20);
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

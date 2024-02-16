package com.mygdx.game.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityManager.Entity;

public class AI extends Entity {
    private final Texture texture;
    private float x;
    private float y;
    float width;
    float height;
    private boolean movingRight = true; // Initial direction

    public AI(float x, float y, float speed, String image, float width, float height){
        super(x, y, speed);
        this.width = width;
        this.height = height;
        this.texture = new Texture(Gdx.files.internal(image));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.width, this.height);
    }

    public void movement(float deltaTime, float speed) {
        // Calculate movement based on direction
        float movement = movingRight ? speed : -speed;

        // Update position based on movement
        x += movement * deltaTime;

        // Check if AI has reached the edge of the screen
        if (x < 0) {
            x = 0; // Reset position to prevent going off-screen
            movingRight = true; // Change direction to right
        } else if (x > (float) Gdx.graphics.getWidth() /2) {
            x = (float) Gdx.graphics.getWidth() /2; // Reset position
            System.out.println(Gdx.graphics.getWidth() + "," + texture.getWidth());
            movingRight = false; // Change direction to left
        }
    }

    public Texture getTexture() {
        return texture;
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public void updateExistence() {

    }

    // Other methods for controlling the entity's behavior
}

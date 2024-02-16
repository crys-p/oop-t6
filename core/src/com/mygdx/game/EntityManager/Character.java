package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Character extends Entity {
    private final Texture texture;

    public Character(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY);
        this.texture = new Texture(Gdx.files.internal(image));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    public void movement(float deltaTime) { //shermaine
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
    public void logConsole() {
        System.out.printf("I am a player at %f, %f%n", this.getX(), this.getY());
    }
}

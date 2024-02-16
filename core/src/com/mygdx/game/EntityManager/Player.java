package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {
    private final Texture texture;

    public Player(float x, float y, float speed, String image) {
        super(x, y, speed);
        this.texture = new Texture(Gdx.files.internal(image));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    public void update(float deltaTime) { //shermaine
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
    public void updateExistence() {
        System.out.printf("I am a player at %f, %f%n", this.getX(), this.getY());
    }
}

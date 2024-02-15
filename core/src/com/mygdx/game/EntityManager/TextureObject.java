package com.mygdx.game.EntityManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.EntityManager.Entity;

import java.util.Random;

public class TextureObject extends Entity {
    private final Texture texture;

    private boolean AIControlled;

    // Constructor for random sprite position
    public TextureObject(String image, boolean AIControlled) {
        super(new Random());
        this.texture = new Texture(Gdx.files.internal(image));
        this.AIControlled = AIControlled;
    }

    // Constructor with fixed sprite position
    public TextureObject(String image, float x, float y, float speed, boolean AIControlled) {
        super(x, y, speed);
        this.texture = new Texture(Gdx.files.internal(image));
        this.AIControlled = AIControlled;
    }

    // Draw Sprite on SpriteBatch
    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    public Texture getTexture() {
        return this.texture;
    }

    public boolean getAIControl() {
        return AIControlled;
    }

    // Moves LR
    public void moveUserControlled() {
        float leftX = this.getX() - this.getSpeed() * Gdx.graphics.getDeltaTime();
        float rightX = this.getX() + this.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) this.setX(leftX);
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) this.setX(rightX);
    }

    // Drops from sky
    public void moveAIControlled() {
        this.decrementY(this.getSpeed());
        if (this.getY() <= 0) {
            this.setY(400);
            if (this.getSpeed() + 2 < 10) {
                this.incrementSpeed(1);
            }
        }
    }

    public void update() {
        System.out.printf("In a texture at %f, %f%n", this.getX(), this.getY());
    }

}

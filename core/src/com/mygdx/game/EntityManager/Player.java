package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {
    private final Texture texture;
    private float width, height;

    public Player(float x, float y, float speed, String image, float width, float height){
        super(x, y, speed);
        this.width = width;
        this.height = height;
        this.texture = new Texture(Gdx.files.internal(image));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.width, this.height);
    }

    public void updateExistence() {
        System.out.printf("I am a player at %f, %f%n", this.getX(), this.getY());
    }
}

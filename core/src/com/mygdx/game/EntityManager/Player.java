package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity {
    private final Texture texture;

    public Player(float x, float y, float speed, String image){
        super(x, y, speed);
        this.texture = new Texture(Gdx.files.internal(image));
    }

    public void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    public void updateExistence() {
        System.out.printf("I am a player at %f, %f%n", this.getX(), this.getY());
    }
}

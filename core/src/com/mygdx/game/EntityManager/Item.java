package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class Item extends Entity {
    private final Texture texture;

    protected Item(Random random, String image){
        super(random);
        this.texture = new Texture(Gdx.files.internal(image));
    }

    protected void draw(SpriteBatch batch) {
        batch.draw(this.texture, this.getX(), this.getY(), this.texture.getWidth(), this.texture.getHeight());
    }

    protected void logConsole() {
        System.out.printf("I am item at %f, %f%n", this.getX(), this.getY());
    }

    @Override
    protected void movement(float deltaTime) {

    }
}

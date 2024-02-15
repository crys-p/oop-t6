package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    private List<Entity> entityList = new ArrayList<>();

    // Constructor
    public EntityManager() {}

    public void createEntities() {
        // Creating Sprites
        entityList.add(new TextureObject("bucket.png", 300, 0, 200, false));
        for (int i = 0; i < 10; i++) {
            entityList.add(new TextureObject("droplet.png", true));
        }
        // Creating Shapes
        entityList.add(new Circle(200, 300, 400, 50, Color.RED));
        entityList.add(new Triangle(300, 200, 40, Color.GREEN, 50));
    }

    public void drawEntities(SpriteBatch batch, ShapeRenderer shape) {
        for (Entity e: entityList) {
            if (e instanceof TextureObject) {
                e.draw(batch);
            } else {
                e.draw(shape);
            }
        }
    }

    public void entityMovement() {
        for (Entity e: entityList) {
            if (e instanceof TextureObject && ((TextureObject) e).getAIControl()) {
                e.moveAIControlled();
            } else {
                e.moveUserControlled();
            }
        }
    }
}

package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {
    private List<Entity> entityList = new ArrayList<>();

    // Constructor
    public EntityManager() {}

    public void createEntities() {
        Random random = new Random();
        // Creating Sprites
        entityList.add(new Player(300, 0, 200, "bucket.png"));
        for (int i = 0; i < 10; i++) {
            entityList.add(new Item(random, "droplet.png"));
        }
        // Creating Shapes
        entityList.add(new Circle(200, 300, 400, Color.RED, 50));
        entityList.add(new Triangle(300, 200, 40, Color.GREEN, 50));
    }

    public void drawEntities(SpriteBatch batch, ShapeRenderer shape) {
        for (Entity e: entityList) {
            if (e instanceof Player || e instanceof Item) {
                e.draw(batch);
            } else {
                e.draw(shape);
            }
        }
    }

    public void entityMovement() {

    }
}

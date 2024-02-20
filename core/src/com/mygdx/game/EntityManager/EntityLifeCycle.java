package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

// This interface is implemented by EntityManager for the creation, drawing and deleting in SceneManager
public interface EntityLifeCycle {
    void createCharacter(int quantity, float x, float y, float velocityX, float velocityY);

    void createCircle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float radius);

    void createTriangle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float sideLength);

    // Create item at specific location
    void createItem(int quantity, float x, float y, float velocityX, float velocityY);

    // Create item at random x positions
    void createItemRandomX(int quantity, Random random, float y, float velocityX, float velocityY);

    // Draw all entities in entity list
    void drawEntities(SpriteBatch batch, ShapeRenderer shape);

    // Delete all entities in entity list
    void deleteAllEntities();
}

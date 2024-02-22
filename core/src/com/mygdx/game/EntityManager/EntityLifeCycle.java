package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

// This interface is implemented by EntityManager for the creation, drawing and deleting in SceneManager
public interface EntityLifeCycle {
    void createCharacter(int quantity, float x, float y, float speed, float velocityY, String controls);

    void createEnemyRandom(int quantity, Random random, float velocityX, float VelocityY);

    // Create item at specific location
    void createCollectible(int quantity, float x, float y, float speed, float velocityY);

    // Create item at random x positions
    void createCollectibleRandom(int quantity, Random random, float velocityX, float velocityY);

    // Draw all Sprite in entity list
    void drawAllEntities(SpriteBatch batch);

    // Delete all entities in entity list
    void deleteAllEntities();
}

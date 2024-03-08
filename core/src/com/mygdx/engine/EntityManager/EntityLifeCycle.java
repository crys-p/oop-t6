package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

// This interface is implemented by EntityManager for the creation, drawing and deleting in SceneManager
public interface EntityLifeCycle {
    // Create character at different position
    void createCharacter(int quantity, float x, float y, float speed, String controls);
    void createCharacter(int quantity, Random random, float speed, String controls);

    // Create enemies at different positions
    void createEnemy(int quantity, float x, float y);
    void createEnemy(int quantity, Random random);

    // Create collectibles at different positions
    void createCollectible(int quantity, float x, float y);
    void createCollectible(int quantity, Random random);

    // Draw all Sprite in entity list
    void drawAllEntities(SpriteBatch batch);

    // Delete all entities in entity list
    void deleteAllEntities();
}

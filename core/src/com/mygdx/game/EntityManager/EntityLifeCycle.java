package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

// This interface is implemented by EntityManager for the creation, drawing and deleting in SceneManager
public interface EntityLifeCycle {
    // Create character at defined position
    void createCharacter(int quantity, float x, float y, float speed, String controls);

    // Create enemies at random positions
    void createEnemyRandom(int quantity, Random random);

    // Create collectibles at random positions
    void createCollectibleRandom(int quantity, Random random);

    // Draw all Sprite in entity list
    void drawAllEntities(SpriteBatch batch);

    // Delete all entities in entity list
    void deleteAllEntities();
}

package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlayerControlConfigs;

import java.util.Random;

// This interface is implemented by EntityManager for the drawing and deleting in SceneManager
public interface EntityLifeCycle {
    // Draw all Sprite in entity list
    void drawAllEntities(SpriteBatch batch);

    // Delete all entities in entity list
    void deleteAllEntities();
}

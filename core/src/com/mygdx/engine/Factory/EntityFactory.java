package com.mygdx.engine.Factory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Movement.Movement;
import com.mygdx.game.GameFactory.TextureFactory;
import com.mygdx.game.entities.EntityType;

import java.util.Random;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;


public abstract class EntityFactory {

    protected EntityManager entityManager;
    protected TextureFactory textureFactory;

    public EntityFactory(EntityManager entityManager, TextureFactory textureFactory) {
        this.entityManager = entityManager;
        this.textureFactory = textureFactory;
    }

    // Create entities at fixed position
    public void create(EntityType type, int quantity, float x, float y, float speed, Movement movement) {
        Entity entity;
        for (int i = 0; i < quantity; i++) {
            entity = createSpecifiedEntity(type, x, y, speed, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    // Create entities at random position
    public void create(EntityType type, int quantity, Random random, float speed, Movement movement) {
        Entity entity;
        for (int i = 0; i < quantity; i++) {
            float randomX = random.nextFloat() * SCREEN_WIDTH - 50;
            float randomY = random.nextFloat() * SCREEN_HEIGHT;
            entity = createSpecifiedEntity(type, randomX, randomY, speed, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    protected abstract Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Movement movement);
}
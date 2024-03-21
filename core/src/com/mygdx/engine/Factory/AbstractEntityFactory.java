package com.mygdx.engine.Factory;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.MovementStrategy.Movement;

import java.util.Random;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;


public abstract class AbstractEntityFactory {

    protected EntityManager entityManager;

    public AbstractEntityFactory(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Create entities at fixed position
    public void create(int typeId, int quantity, float x, float y, float speed, Movement movement) {
        Entity entity;
        for (int i = 0; i < quantity; i++) {
            entity = createSpecifiedEntity(typeId, x, y, speed, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    // Create entities at random position within screen width
    public void create(int typeId, int quantity, Random random, float speed, Movement movement) {
        Entity entity;
        for (int i = 0; i < quantity; i++) {
            float randomX = random.nextFloat() * SCREEN_WIDTH - 50;
            float randomY = random.nextFloat() * SCREEN_HEIGHT;
            entity = createSpecifiedEntity(typeId, randomX, randomY, speed, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    protected abstract Entity createSpecifiedEntity(int typeId, float x, float y, float speed, Movement movement);
}


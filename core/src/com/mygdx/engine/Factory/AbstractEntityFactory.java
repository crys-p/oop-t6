package com.mygdx.engine.Factory;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.MovementStrategy.Movement;

import java.util.Random;

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

    // Create entities at random position within screen width -> requires game specific scene width/height for random position
    public abstract void create(int typeId, int quantity, Random random, float speed, Movement movement);

    protected abstract Entity createSpecifiedEntity(int typeId, float x, float y, float speed, Movement movement);
}


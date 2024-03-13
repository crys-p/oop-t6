package com.mygdx.engine.CollisionManager;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.Collision.CollisionHandler;

import java.util.ArrayList;

public class CollisionDetection {
    private final EntityManager entityManager;

    private final ArrayList<Entity> collidableEntityList;

    protected CollisionDetection(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.collidableEntityList = new ArrayList<>();
    }

    protected void setCollidables() {
        // Clear existing maps if any
        collidableEntityList.clear();
        // Add latest items to map
        collidableEntityList.addAll(entityManager.getCollidableEntities());
    }

    protected void detectCollisions(CollisionHandler collisionHandler) {
        for (int i = 0; i < collidableEntityList.size(); i++) {
            Entity entityA = collidableEntityList.get(i);
            for (int j = i + 1; j < collidableEntityList.size(); j++) {
                Entity entityB = collidableEntityList.get(j);
                if (entityA.getBoundingBox().overlaps(entityB.getBoundingBox())) {
                    collisionHandler.handleCollision(entityA, entityB);
                }
            }
        }
    }
}

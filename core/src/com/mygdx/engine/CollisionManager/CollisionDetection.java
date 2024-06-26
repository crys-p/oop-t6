package com.mygdx.engine.CollisionManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.Collision.CollisionHandlers;

import java.util.ArrayList;

public class CollisionDetection {
    private final EntityManager entityManager;

    private final ArrayList<iCollidable> collidableEntityList;

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

    protected void detectCollisions(CollisionHandlers collisionHandlers) {
        for (int i = 0; i < collidableEntityList.size(); i++) {
            iCollidable collidableA = collidableEntityList.get(i);
            for (int j = i + 1; j < collidableEntityList.size(); j++) {
                iCollidable collidableB = collidableEntityList.get(j);
                if (collidableA.getBoundingBox().overlaps(collidableB.getBoundingBox())) {
                    collisionHandlers.handleCollision(collidableA, collidableB);
                }
            }
        }
    }
}

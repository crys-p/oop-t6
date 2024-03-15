package com.mygdx.engine.Factory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Movement.Movement;
import com.mygdx.engine.Movement.PlayerMovement;
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

    public void create(EntityType type, int quantity, float x, float y, float speed, Movement movement) {
        Entity entity = null;
        for (int i = 0; i < quantity; i++) {
            Texture texture = textureFactory.getTexture(type);
            entity = createSpecifiedEntity(type, x, y, speed, texture, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    public void create(EntityType type, int quantity, Random random, float speed, Movement movement) {
        Entity entity = null;
        for (int i = 0; i < quantity; i++) {
            Texture texture = textureFactory.getTexture(type);
            float randomX = random.nextFloat() * SCREEN_WIDTH - 50;
            float randomY = random.nextFloat() * SCREEN_HEIGHT;
            System.out.println("Creating Entity: " + type + "at position: " + randomX + "," + randomY);
            entity = createSpecifiedEntity(type, randomX, randomY, speed, texture, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    protected abstract Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Texture texture, Movement movement);
}
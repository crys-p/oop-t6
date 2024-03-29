package com.mygdx.game.GameFactories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.NonPlayableCharacter;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Factory.AbstractEntityFactory;
import com.mygdx.engine.MovementStrategy.Movement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.GameEntities.*;

import java.util.Random;

import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;
import static com.mygdx.engine.SceneManager.SceneManager.SCENE_WIDTH;


public class NonPlayableEntityFactory extends AbstractEntityFactory {
    private GameTextureFactory gameTextureFactory;

    public NonPlayableEntityFactory(EntityManager entityManager, GameTextureFactory gameTextureFactory) {
        super(entityManager);
        this.gameTextureFactory = gameTextureFactory;
        SimulationManager.getInstance().logInfo("NonPlayable Entity Factory initialized"); // Log initialization message
    }

    public void create(int typeId, int quantity, float x, float y, float speed, Movement movement, int gamePoints) {
        Entity entity;
        for (int i = 0; i < quantity; i++) {
            entity = createSpecifiedEntity(typeId, x, y, speed, movement, gamePoints);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    @Override
    public void create(int typeId, int quantity, Random random, float speed, Movement movement) {
        create(typeId, quantity, random, speed, movement, 0); // Default game value to 0
    }

    public void create(int typeId, int quantity, Random random, float speed, Movement movement, int gamePoints) {
        float textureWidth = gameTextureFactory.getTextureWidth(typeId);
        float textureHeight= gameTextureFactory.getTextureHeight(typeId);
        for (int i = 0; i < quantity; i++) {
            float randomX = random.nextInt(SCENE_WIDTH - (int) textureWidth);
            float randomY = random.nextInt(SCENE_HEIGHT - (int) textureHeight);
            Entity entity = createSpecifiedEntity(typeId, randomX, randomY, speed, movement, gamePoints);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }


    protected NonPlayableCharacter createSpecifiedEntity(int typeId, float x, float y, float speed, Movement movement) {
        return createSpecifiedEntity(typeId, x, y, speed, movement, 0); // Default game value to 0
    }

    // Specific implementation overloaded with randomly generated entities and game value (points / damage)
    protected NonPlayableCharacter createSpecifiedEntity(int typeId, float x, float y, float speed, Movement movement, int gameValue) {
        try {
            AIMovement aiMovement = (AIMovement) movement;
            EntityType type = EntityType.getEntityType(typeId);
            Texture texture = gameTextureFactory.getTexture(typeId);
            switch (type) {
                case BOKCHOY: case BROCCOLI: case CARROT: case CABBAGE: case VEGGIECART:
                    return new Vegetable(x, y, speed, texture, aiMovement, type, gameValue);
                case APPLE: case BANANA: case WATERMELON: case BIGAPPLE:
                    return new Fruit(x, y, speed, texture, aiMovement, type, gameValue);
                case ICECREAM: case COOKIE: case CUPCAKE: case DOUGHNUT: case SUNDAE:
                    return new Dessert(x, y, speed, texture, aiMovement, gameValue);
                case DRUMSTICK: case FRIES: case BURGER: case SODA:
                    return new FastFood(x, y, speed, texture, aiMovement, gameValue);
                case vBRICKWALL: case hBRICKWALL:
                    return new Wall(x, y, speed, texture, aiMovement);
                default:
                    SimulationManager.getInstance().logError("Creating unknown entity type: " + type);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Movement is not an instance of AIMovement.", e);
        }
        return null;
    }
}
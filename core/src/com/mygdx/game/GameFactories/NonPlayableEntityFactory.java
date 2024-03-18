package com.mygdx.game.GameFactories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Factory.AbstractEntityFactory;
import com.mygdx.engine.MovementStrategy.Movement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.*;


public class NonPlayableEntityFactory extends AbstractEntityFactory {
    private GameTextureFactory gameTextureFactory;

    public NonPlayableEntityFactory(EntityManager entityManager, GameTextureFactory gameTextureFactory) {
        super(entityManager);
        this.gameTextureFactory = gameTextureFactory;
    }


    @Override
    protected Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Movement movement) {
        try {
            AIMovement aiMovement = (AIMovement) movement;
            Texture texture = gameTextureFactory.getTexture(type);
            switch (type) {
                case BOKCHOY: case BROCCOLI: case CARROT: case CABBAGE:
                    return new Vegetable(x, y, speed, texture, aiMovement);
                case APPLE: case BANANA: case WATERMELON:
                    return new Fruit(x, y, speed, texture, aiMovement);
                case ICECREAM: case COOKIE: case CUPCAKE: case DOUGHNUT: case SUNDAE:
                    return new Dessert(x, y, speed, texture, aiMovement);
                case DRUMSTICK: case FRIES: case BURGER: case SODA:
                    return new FastFood(x, y, speed, texture, aiMovement);
                default:
                    SimulationManager.getInstance().logError("Creating unknown entity type: " + type);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Movement is not an instance of AIMovement.", e);
        }
        return null;
    }

}
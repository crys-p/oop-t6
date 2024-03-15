package com.mygdx.engine.Factory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Movement.Movement;
import com.mygdx.engine.Movement.PlayerMovement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.Character;
import com.mygdx.game.entities.Collectible;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.EntityType;


public class NonPlayableEntityFactory extends EntityFactory {
    public NonPlayableEntityFactory(EntityManager entityManager, TextureFactory textureFactory) {
        super(entityManager, textureFactory);
    }


    @Override
    protected Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Texture texture, Movement movement) {
        try {
            AIMovement aiMovement = (AIMovement) movement;
            switch (type) {
                case ENEMY:
                    return new Enemy(x, y, speed, texture, aiMovement);
                case COLLECTIBLE:
                    return new Collectible(x, y, speed, texture, aiMovement);
                default:
                    SimulationManager.getInstance().logError("Creating unknown entity type: " + type);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Movement is not an instance of PlayerMovement.", e);
        }
        return null;
    }

}
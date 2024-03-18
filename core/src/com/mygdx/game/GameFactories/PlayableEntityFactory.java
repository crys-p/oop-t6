package com.mygdx.game.GameFactories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Factory.AbstractEntityFactory;
import com.mygdx.engine.MovementStrategy.Movement;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.Boy;
import com.mygdx.game.entities.EntityType;


public class PlayableEntityFactory extends AbstractEntityFactory {
    private GameTextureFactory gameTextureFactory;
    public PlayableEntityFactory(EntityManager entityManager, GameTextureFactory gameTextureFactory) {
        super(entityManager);
        this.gameTextureFactory = gameTextureFactory;
    }


    @Override
    protected Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Movement movement) {
        try {
            PlayerMovement playerMovement = (PlayerMovement) movement;
            Texture[] textures = gameTextureFactory.getTextures(type);
            switch (type) {
                case BOY:
                    return new Boy(x, y, speed, textures[1], textures, playerMovement);
                // add other playable entities
                default:
                    SimulationManager.getInstance().logError("Creating unknown entity type: " + type);
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Movement is not an instance of PlayerMovement.", e);
        }
        return null;
    }

}
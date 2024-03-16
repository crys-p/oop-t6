package com.mygdx.game.GameFactory;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Factory.EntityFactory;
import com.mygdx.engine.Movement.Movement;
import com.mygdx.engine.Movement.PlayerMovement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.Character;
import com.mygdx.game.entities.EntityType;


public class PlayableEntityFactory extends EntityFactory {
    public PlayableEntityFactory(EntityManager entityManager, TextureFactory textureFactory) {
        super(entityManager, textureFactory);
    }


    @Override
    protected Entity createSpecifiedEntity(EntityType type, float x, float y, float speed, Movement movement) {
        try {
            PlayerMovement playerMovement = (PlayerMovement) movement;
            Texture texture = textureFactory.getTexture(type);
            switch (type) {
                case CHARACTER:
                    return new Character(x, y, speed, texture, playerMovement);
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
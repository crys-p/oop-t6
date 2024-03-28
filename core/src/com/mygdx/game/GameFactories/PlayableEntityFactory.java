package com.mygdx.game.GameFactories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.Factory.AbstractEntityFactory;
import com.mygdx.engine.MovementStrategy.Movement;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.GameEntities.Boy;
import com.mygdx.game.GameEntities.EntityType;

import java.util.Random;

import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;
import static com.mygdx.engine.SceneManager.SceneManager.SCENE_WIDTH;


public class PlayableEntityFactory extends AbstractEntityFactory {
    private GameTextureFactory gameTextureFactory;
    public PlayableEntityFactory(EntityManager entityManager, GameTextureFactory gameTextureFactory) {
        super(entityManager);
        this.gameTextureFactory = gameTextureFactory;
        SimulationManager.getInstance().logInfo("Playable Entity Factory initialized"); // Log initialization message
    }


    @Override
    public void create(int typeId, int quantity, Random random, float speed, Movement movement) {
        float textureWidth = gameTextureFactory.getTextureWidth(typeId);
        float textureHeight = gameTextureFactory.getTextureHeight(typeId);
        for (int i = 0; i < quantity; i++) {
            float randomX = random.nextInt(SCENE_WIDTH - (int) textureWidth);
            float randomY = random.nextInt(SCENE_HEIGHT - (int) textureHeight);
            Entity entity = createSpecifiedEntity(typeId, randomX, randomY, speed, movement);
            if (entity != null) {
                entityManager.addEntity(entity);
            }
        }
    }

    @Override
    protected PlayableCharacter createSpecifiedEntity(int typeId, float x, float y, float speed, Movement movement) {
        try {
            PlayerMovement playerMovement = (PlayerMovement) movement;
            EntityType type = EntityType.getEntityType(typeId);
            Texture[] textures = gameTextureFactory.getTextures(typeId);
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
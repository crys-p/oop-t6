package com.mygdx.game.GameFactories;

import com.mygdx.engine.EntityManager.EntityManager;

public class EntityFactoryManager {

    private PlayableEntityFactory playableEntityFactory;
    private NonPlayableEntityFactory nonPlayableEntityFactory;

    public EntityFactoryManager(EntityManager entityManager, GameTextureFactory gameTextureFactory) {
        this.playableEntityFactory = new PlayableEntityFactory(entityManager, gameTextureFactory);
        this.nonPlayableEntityFactory = new NonPlayableEntityFactory(entityManager, gameTextureFactory);
    }

    public PlayableEntityFactory getPlayable() {
        return playableEntityFactory;
    }

    public NonPlayableEntityFactory getNonPlayable() {
        return nonPlayableEntityFactory;
    }
}

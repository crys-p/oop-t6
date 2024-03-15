package com.mygdx.game.GameFactory;

import com.mygdx.engine.EntityManager.EntityManager;

public class EntityFactoryManager {

    private PlayableEntityFactory playableEntityFactory;
    private NonPlayableEntityFactory nonPlayableEntityFactory;

    public EntityFactoryManager(EntityManager entityManager, TextureFactory textureFactory) {
        this.playableEntityFactory = new PlayableEntityFactory(entityManager, textureFactory);
        this.nonPlayableEntityFactory = new NonPlayableEntityFactory(entityManager, textureFactory);
    }

    public PlayableEntityFactory getPlayableEntityFactory() {
        return playableEntityFactory;
    }

    public NonPlayableEntityFactory getNonPlayableEntityFactory() {
        return nonPlayableEntityFactory;
    }
}

package com.mygdx.engine.Factory;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.Factory.NonPlayableEntityFactory;
import com.mygdx.engine.Factory.PlayableEntityFactory;
import com.mygdx.engine.Factory.TextureFactory;

public class EntityFactoryOwner {

    private PlayableEntityFactory playableEntityFactory;
    private NonPlayableEntityFactory nonPlayableEntityFactory;

    public EntityFactoryOwner(EntityManager entityManager, TextureFactory textureFactory) {
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

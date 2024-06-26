package com.mygdx.game.Collision;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.CollisionManager.iCollidable;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.game.GameEntities.*;
import com.mygdx.game.player.GamePlayerManager;

public class CollisionHandlers {

    private final CharacterEnemyHandler characterEnemyHandler;
    private final CharacterCollectibleHandler characterCollectibleHandler;

    public CollisionHandlers(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
        this.characterEnemyHandler = new CharacterEnemyHandler(soundManager, gameplayerManager);
        this.characterCollectibleHandler = new CharacterCollectibleHandler(entityManager, soundManager, gameplayerManager, sceneManager);
    }


    public void handleCollision(iCollidable entityA, iCollidable entityB) {
        if ((entityA instanceof GameCharacter && entityB instanceof Enemy) || (entityB instanceof GameCharacter && entityA instanceof Enemy)) {
            characterEnemyHandler.characterEnemyCollision(entityA, entityB);
        }
        if ((entityA instanceof GameCharacter && entityB instanceof Collectible) || (entityB instanceof GameCharacter && entityA instanceof Collectible)) {
            characterCollectibleHandler.characterCollectibleCollision(entityA, entityB);
        }
    }

}

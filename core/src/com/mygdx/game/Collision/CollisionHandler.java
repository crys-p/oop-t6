package com.mygdx.game.Collision;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.EntityManager.iCollidable;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.game.entities.Collectible;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.player.GamePlayerManager;

public class CollisionHandler {

    protected final EntityManager entityManager;
    protected final SoundManager soundManager;
    protected final GamePlayerManager gameplayerManager;

    public CollisionHandler(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.gameplayerManager = gameplayerManager;
    }


    public void handleCollision(iCollidable entityA, iCollidable entityB) {
        if ((entityA instanceof PlayableCharacter && entityB instanceof Enemy) || (entityB instanceof PlayableCharacter && entityA instanceof Enemy)) {
            characterEnemyCollision(entityA, entityB);
        }
        if ((entityA instanceof PlayableCharacter && entityB instanceof Collectible) || (entityB instanceof PlayableCharacter && entityA instanceof Collectible)) {
            characterCollectibleCollision(entityA, entityB);
        }
        // Add other pairs
    }

    // Handler methods
    private void characterEnemyCollision(iCollidable entityA, iCollidable entityB) {
        Enemy enemy;
        PlayableCharacter playableCharacter;
        if (entityA instanceof Enemy) {
            enemy = (Enemy) entityA;
            playableCharacter = (PlayableCharacter) entityB;
        } else {
            enemy = (Enemy) entityB;
            playableCharacter = (PlayableCharacter) entityA;
        }
        // Remove the collectible from the entity manager
        entityManager.removeEntity(enemy);
        // Reduce health of player based on enemy damage
        gameplayerManager.takeDamage(playableCharacter, enemy);
        // Play hit sound
        soundManager.playSoundEffect(SoundEffectType.HIT);
    }

    private void characterCollectibleCollision(iCollidable entityA, iCollidable entityB) {
        Collectible collectible;
        PlayableCharacter playableCharacter;
        int characterID;
        if (entityA instanceof Collectible) {
            collectible = (Collectible) entityA;
            playableCharacter = (PlayableCharacter) entityB;
        } else {
            collectible = (Collectible) entityB;
            playableCharacter = (PlayableCharacter) entityA;
        }
        characterID = entityManager.getEntityID(playableCharacter);
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectible);
        // Add the collided collectible to the player's inventory
        gameplayerManager.addItemToInventory(characterID);
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);
    }
}

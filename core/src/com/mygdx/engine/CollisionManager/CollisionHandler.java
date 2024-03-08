package com.mygdx.engine.CollisionManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.engine.SoundManager.SoundEffectType;

public class CollisionHandler {
    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final PlayerManager playerManager;

    protected CollisionHandler(EntityManager entityManager, SoundManager soundManager, PlayerManager playerManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.playerManager = playerManager;
    }

    protected void handleCharacterEnemyCollision(int characterID, int enemyID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(enemyID);
        // Reduce health of player based on enemy damage
        playerManager.takeDamage(characterID, enemyID);
        // Play hit sound
        soundManager.playSoundEffect(SoundEffectType.HIT);
    }

    protected void handleCharacterCollectibleCollision(int characterID, int collectibleID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectibleID);
        // Add the collided collectible to the player's inventory
        playerManager.addItemToInventory(characterID);
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);
    }
}

package com.mygdx.game.CollisionManager;

import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SoundManager.SoundManager;
import com.mygdx.game.SoundManager.SoundEffectType;

public class CollisionHandler {
    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final PlayerControlManager playerControlManager;

    public CollisionHandler(EntityManager entityManager, SoundManager soundManager, PlayerControlManager playerControlManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.playerControlManager = playerControlManager;
    }

    public void handleCharacterEnemyCollision(int characterID, int enemyID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(enemyID);
        // Reduce health of player based on enemy damage
        playerControlManager.takeDamage(characterID, enemyID);
        // Play hit sound
        soundManager.playSoundEffect(SoundEffectType.HIT);
    }

    public void handleCharacterCollectibleCollision(int characterID, int collectibleID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectibleID);
        // Add the collided collectible to the player's inventory
        playerControlManager.addItemToInventory(characterID);
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);
    }
}

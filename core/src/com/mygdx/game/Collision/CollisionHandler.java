package com.mygdx.game.Collision;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.game.entities.Character;
import com.mygdx.game.entities.Collectible;
import com.mygdx.game.entities.Enemy;

public class CollisionHandler {

    protected final EntityManager entityManager;
    protected final SoundManager soundManager;
    protected final PlayerManager playerManager;

    public CollisionHandler(EntityManager entityManager, SoundManager soundManager, PlayerManager playerManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.playerManager = playerManager;
    }


    public void handleCollision(Entity entityA, Entity entityB) {
        if ((entityA instanceof Character && entityB instanceof Enemy) || (entityB instanceof Character && entityA instanceof Enemy)) {
            characterEnemyCollision(entityA, entityB);
        }
        if ((entityA instanceof Character && entityB instanceof Collectible) || (entityB instanceof Character && entityA instanceof Collectible)) {
            characterCollectibleCollision(entityA, entityB);
        }
        // Add other pairs
    }

    // Handler methods
    private void characterEnemyCollision(Entity entityA, Entity entityB) {
        Enemy enemy;
        Character character;
        if (entityA instanceof Enemy) {
            enemy = (Enemy) entityA;
            character = (Character) entityB;
        } else {
            enemy = (Enemy) entityB;
            character = (Character) entityA;
        }
        // Remove the collectible from the entity manager
        entityManager.removeEntity(enemy);
        // Reduce health of player based on enemy damage
        playerManager.takeDamage(character, enemy);
        // Play hit sound
        soundManager.playSoundEffect(SoundEffectType.HIT);
    }

    private void characterCollectibleCollision(Entity entityA, Entity entityB) {
        Collectible collectible;
        Character character;
        int characterID;
        if (entityA instanceof Collectible) {
            collectible = (Collectible) entityA;
            character = (Character) entityB;
        } else {
            collectible = (Collectible) entityB;
            character = (Character) entityA;
        }
        characterID = entityManager.getEntityID(character);
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectible);
        // Add the collided collectible to the player's inventory
        playerManager.addItemToInventory(characterID);
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);
    }
}

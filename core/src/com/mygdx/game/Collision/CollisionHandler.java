package com.mygdx.game.Collision;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.EntityManager.iCollidable;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.game.GameEntities.*;
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
        if ((entityA instanceof GameCharacter && entityB instanceof Enemy) || (entityB instanceof GameCharacter && entityA instanceof Enemy)) {
            characterEnemyCollision(entityA, entityB);
        }
        if ((entityA instanceof GameCharacter && entityB instanceof Collectible) || (entityB instanceof GameCharacter && entityA instanceof Collectible)) {
            characterCollectibleCollision(entityA, entityB);
        }
        // Add other pairs if any
    }

    // Handling for Character(Player) & Enemy Collision
    private void characterEnemyCollision(iCollidable entityA, iCollidable entityB) {
        Enemy enemy;
        GameCharacter gameCharacter;
        if (entityA instanceof Enemy) {
            enemy = (Enemy) entityA;
            gameCharacter = (GameCharacter) entityB;
        } else {
            enemy = (Enemy) entityB;
            gameCharacter = (GameCharacter) entityA;
        }
        if (enemy instanceof Wall) {
            gameCharacter.moveBackwards();
        } else {
            // Remove the collectible from the entity manager
            entityManager.removeEntity(enemy);
            // Reduce health of player based on enemy damage
            gameplayerManager.takeDamage(gameCharacter, enemy);
            // Play hit sound
            soundManager.playSoundEffect(SoundEffectType.HIT);
        }
    }

    // Handling for Character(Player) & Collectible Collision
    private void characterCollectibleCollision(iCollidable entityA, iCollidable entityB) {
        Collectible collectible;
        GameCharacter gameCharacter;
        if (entityA instanceof Collectible) {
            collectible = (Collectible) entityA;
            gameCharacter = (GameCharacter) entityB;
        } else {
            collectible = (Collectible) entityB;
            gameCharacter = (GameCharacter) entityA;
        }
        int characterID = entityManager.getEntityID(gameCharacter);
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectible);

        // Add the collided collectible to the player's inventory, add points //TODO: separate this based on game mechanics
        gameplayerManager.addItemToInventory(characterID);
        gameplayerManager.addPoints(characterID, collectible.getPoints());

        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);

    }

    // ALTER THIS TO BE USED INSIDE characterCollectibleCollision
    private void characterVegetableCollision(GameCharacter character, Vegetable vegetable) {
        int characterID = entityManager.getEntityID(character);
        gameplayerManager.addPoints(characterID, vegetable.getPoints());
    }
}

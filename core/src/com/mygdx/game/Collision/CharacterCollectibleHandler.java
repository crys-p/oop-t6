package com.mygdx.game.Collision;

import com.mygdx.engine.CollisionManager.iCollidable;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.GameEntities.Collectible;
import com.mygdx.game.GameEntities.GameCharacter;
import com.mygdx.game.GameEntities.Vegetable;
import com.mygdx.game.player.GamePlayerManager;

public class CharacterCollectibleHandler {

    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final GamePlayerManager gameplayerManager;
    private final SceneManager sceneManager;

    public CharacterCollectibleHandler(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.gameplayerManager = gameplayerManager;
        this.sceneManager = sceneManager;
    }

    // Handling for Character(Player) & Collectible Collision
    protected void characterCollectibleCollision(iCollidable entityA, iCollidable entityB) {
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
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);

        // Add the collided collectible to the player's inventory, add points
        // if item is vegetable, add to inventory
        if (collectible instanceof Vegetable) {
            characterCollectVegetable(characterID, (Vegetable) collectible);
        }
//        gameplayerManager.addItemToInventory(characterID, );
        gameplayerManager.addPoints(characterID, collectible.getPoints());
    }

    // ALTER THIS TO BE USED INSIDE characterCollectibleCollision
    private void characterCollectVegetable(int characterID, Vegetable vegetable) {
        gameplayerManager.addItemToInventory(characterID, vegetable.getMyType());
    }

}

package com.mygdx.game.CollisionManager;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SoundManager.SoundEffectType;
import com.mygdx.game.SoundManager.SoundManager;

import java.util.HashMap;
import java.util.List;

public class CollisionManager {
    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final PlayerControlManager playerControlManager;
    private HashMap<Rectangle, Integer> characterMap;
    private HashMap<Rectangle, Integer> enemyMap;
    private HashMap<Rectangle, Integer> collectibleMap;


    public CollisionManager(EntityManager entityManager, SoundManager soundManager, PlayerControlManager playerControlManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.playerControlManager = playerControlManager;
        characterMap = new HashMap<>();
        enemyMap = new HashMap<>();
        collectibleMap = new HashMap<>();
    }

    public void setCollidables() {
        // Clear existing maps if any
        characterMap.clear();
        enemyMap.clear();
        collectibleMap.clear();
        // Add latest items to map
        HashMap<Rectangle, Integer> allCharacters = entityManager.getCharacterBoundingBoxes();
        characterMap.putAll(allCharacters);
        HashMap<Rectangle, Integer> allEnemies = entityManager.getEnemyBoundingBoxes();
        enemyMap.putAll(allEnemies);
        HashMap<Rectangle, Integer> allCollectibles = entityManager.getCollectibleBoundingBoxes();
        collectibleMap.putAll(allCollectibles);
    }

    public void detectCollisions() {
        // Loop through different maps to detect collision
        // If collision is being detected, handle collision reaction using handleCharacterCollectibleCollision etc.
        for (Rectangle enemyRect : enemyMap.keySet()) {
            for (Rectangle charRect : characterMap.keySet()) {
                if (charRect.overlaps(enemyRect)) {
                    int charID = characterMap.get(charRect);
                    int enemyID = enemyMap.get(enemyRect);
                    handleCharacterEnemyCollision(charID, enemyID);
                }
            }
        }
        for (Rectangle collectibleRect : collectibleMap.keySet()) {
            for (Rectangle charRect : characterMap.keySet()) {
                if (charRect.overlaps(collectibleRect)) {
                    int charID = characterMap.get(charRect);
                    int collectibleID = collectibleMap.get(collectibleRect);
                    handleCharacterCollectibleCollision(charID, collectibleID);
                }
            }
        }
    }

    private void handleCharacterCollectibleCollision(int characterID, int collectibleID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(collectibleID);
        // Add the collided collectible to the player's inventory
        playerControlManager.addItemToInventory(characterID);
        // Play collected sound
        soundManager.playSoundEffect(SoundEffectType.COLLECT);
    }

    private void handleCharacterEnemyCollision(int characterID, int enemyID) {
        // Remove the collectible from the entity manager
        entityManager.removeEntity(enemyID);
        // Reduce health of player based on enemy damage
        playerControlManager.takeDamage(characterID, enemyID);
        // Play hit sound
        soundManager.playSoundEffect(SoundEffectType.HIT);
    }
}
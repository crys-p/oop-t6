package com.mygdx.game.CollisionManager;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SoundManager.SoundManager;

import java.util.HashMap;

public class CollisionDetection {
    private final EntityManager entityManager;

    private final HashMap<Rectangle, Integer> characterMap;
    private final HashMap<Rectangle, Integer> enemyMap;
    private final HashMap<Rectangle, Integer> collectibleMap;

    public CollisionDetection(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.characterMap = new HashMap<>();
        this.enemyMap = new HashMap<>();
        this.collectibleMap = new HashMap<>();
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

    public void detectCollisions(CollisionHandler collisionHandler) {
        // Loop through different maps to detect collision
        // If collision is being detected, handle collision reaction using handleCharacterCollectibleCollision etc.
        for (Rectangle enemyRect : enemyMap.keySet()) {
            for (Rectangle charRect : characterMap.keySet()) {
                if (charRect.overlaps(enemyRect)) {
                    int charID = characterMap.get(charRect);
                    int enemyID = enemyMap.get(enemyRect);
                    collisionHandler.handleCharacterEnemyCollision(charID, enemyID);
                }
            }
        }
        for (Rectangle collectibleRect : collectibleMap.keySet()) {
            for (Rectangle charRect : characterMap.keySet()) {
                if (charRect.overlaps(collectibleRect)) {
                    int charID = characterMap.get(charRect);
                    int collectibleID = collectibleMap.get(collectibleRect);
                    collisionHandler.handleCharacterCollectibleCollision(charID, collectibleID);
                    System.out.println("THERE");
                }
            }
        }
    }
}

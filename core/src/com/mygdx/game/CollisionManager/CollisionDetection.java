package com.mygdx.game.CollisionManager;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SoundManager.SoundManager;

import java.util.HashMap;

public class CollisionDetection {
    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final PlayerControlManager playerControlManager;


    public CollisionDetection(EntityManager entityManager, SoundManager soundManager, PlayerControlManager playerControlManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.playerControlManager = playerControlManager;
    }

    public void detectCollisions(HashMap<Rectangle, Integer> characterMap, HashMap<Rectangle, Integer> enemyMap, HashMap<Rectangle, Integer> collectibleMap) {

        CollisionHandler collisionHandler = new CollisionHandler(entityManager, soundManager, playerControlManager);

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

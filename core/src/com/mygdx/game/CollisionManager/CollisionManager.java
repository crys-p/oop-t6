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
    private final HashMap<Rectangle, Integer> characterMap;
    private final HashMap<Rectangle, Integer> enemyMap;
    private final HashMap<Rectangle, Integer> collectibleMap;


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
        CollisionDetection collisionDetection = new CollisionDetection(entityManager, soundManager, playerControlManager);
        collisionDetection.detectCollisions(characterMap, enemyMap,collectibleMap);
    }

}
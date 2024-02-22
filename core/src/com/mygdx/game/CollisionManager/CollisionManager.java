package com.mygdx.game.CollisionManager;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.Item;
import com.mygdx.game.PlayerControlManager.Player;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SoundManager.SoundManager;

import java.util.HashMap;
import java.util.List;

public class CollisionManager {
    private EntityManager entityManager;
    private SoundManager soundManager;
    private PlayerControlManager playerControlManager;
    private HashMap<Rectangle, Integer> characterMap;
    private HashMap<Rectangle, Integer> enemyMap;
    private HashMap<Rectangle, Integer> collectibleMap;


    public CollisionManager(EntityManager entityManager, SoundManager soundManager, PlayerControlManager playerControlManager)
    {
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
        soundManager.playSoundEffect(1);
    }

    private void handleCharacterEnemyCollision(int characterID, int enemyID) {
        entityManager.removeEntity(enemyID);
        playerControlManager.takeDamage(characterID, enemyID);
        soundManager.playSoundEffect(2);
    }


//    //shermaine
//    public boolean canMoveLeft(Entity player) {
//        // Check if moving left would not go beyond the left boundary of the screen
//        // You may also check for obstacles or collisions preventing movement left
//        return player.getEntityX() > 0; // Assuming 0 is the left boundary of the screen
//    }
//
//    public boolean canMoveRight(Entity player) {
//        // Check if moving right would not go beyond the right boundary of the screen
//        // You may also check for obstacles or collisions preventing movement right
//        return player.getEntityX() < Config.SCREEN_WIDTH; // Assuming screenWidth is the right boundary of the screen
//    }
}
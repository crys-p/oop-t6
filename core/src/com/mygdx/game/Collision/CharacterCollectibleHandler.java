package com.mygdx.game.Collision;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.CollisionManager.iCollidable;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.GameEntities.Collectible;
import com.mygdx.game.GameEntities.Fruit;
import com.mygdx.game.GameEntities.GameCharacter;
import com.mygdx.game.GameEntities.Vegetable;
import com.mygdx.game.player.GamePlayerManager;

import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;
import static com.mygdx.engine.SceneManager.SceneManager.SCENE_WIDTH;

public class CharacterCollectibleHandler {

    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final GamePlayerManager gameplayerManager;
    private final SceneManager sceneManager;

    private long lastRecoveryTime = 0; // Timestamp of the last collision
    private long cooldownDuration = 1000; // Cooldown is one second

    public CharacterCollectibleHandler(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.gameplayerManager = gameplayerManager;
        this.sceneManager = sceneManager;
    }

    // Handling for Character(Player) & Collectible Collision
    protected void characterCollectibleCollision(iCollidable entityA, iCollidable entityB) {
        Entity[] entities = downcastEntities(entityA, entityB);
        GameCharacter gameCharacter = (GameCharacter) entities[0];
        Collectible collectible = (Collectible) entities[1];
        int characterID = entityManager.getEntityID(gameCharacter);

        // Vegetable specific stuff
        if (collectible instanceof Vegetable) {
            characterCollectVegetable(characterID, (Vegetable) collectible);
        }
        // Fruit specific stuff
        if (collectible instanceof Fruit) {
            characterCollectFruit(characterID, (Fruit)collectible);
        }
    }

    private void characterCollectVegetable(int characterID, Vegetable vegetable) {
        gameplayerManager.addItemToInventory(characterID, vegetable.getMyType());
        // If in GameL1, need to respawn from the corner instead of remove entity.
        if (sceneManager.getCurrentSceneType() == SceneManager.SceneType.GAMEL1) {
            vegetable.respawn(0, MathUtils.random() * SCENE_HEIGHT);
        } else {
            entityManager.removeEntity(vegetable); // remove collectible
        }
        soundManager.playSoundEffect(SoundEffectType.COLLECT); // play sfx
    }

    private void characterCollectFruit(int characterID, Fruit fruit) {
        // if fruit is a recovery fruit
        if (fruit.isRecovery()) {
            // check if cooldown is over
            if (System.currentTimeMillis() - lastRecoveryTime < cooldownDuration) {
                // don't recover health
            } else {
                gameplayerManager.recoverHealth(characterID, fruit.getPoints());
                this.lastRecoveryTime = System.currentTimeMillis();
                soundManager.playSoundEffect(SoundEffectType.HEAL); // play sfx
            }
        }
        else {
            // If in GameL1, need to respawn from the corner instead of remove entity.
            if (sceneManager.getCurrentSceneType() == SceneManager.SceneType.GAMEL1) {
                fruit.respawn(0, MathUtils.random() * SCENE_HEIGHT);
            } else {
                entityManager.removeEntity(fruit); // remove collectible
            }
            soundManager.playSoundEffect(SoundEffectType.COLLECT); // play sfx
            gameplayerManager.addPoints(characterID, fruit.getPoints()); // add points
        }
    }


    private Entity[] downcastEntities(iCollidable entityA, iCollidable entityB) {
        Collectible collectible;
        GameCharacter gameCharacter;

        if (entityA instanceof Collectible) {
            collectible = (Collectible) entityA;
            gameCharacter = (GameCharacter) entityB;
        } else {
            collectible = (Collectible) entityB;
            gameCharacter = (GameCharacter) entityA;
        }
        return new Entity[]{gameCharacter, collectible};
    }

}

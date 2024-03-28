package com.mygdx.game.Collision;

import com.mygdx.engine.CollisionManager.iCollidable;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.GameEntities.Enemy;
import com.mygdx.game.GameEntities.GameCharacter;
import com.mygdx.game.GameEntities.Wall;
import com.mygdx.game.player.GamePlayerManager;

public class CharacterEnemyHandler {

    private final EntityManager entityManager;
    private final SoundManager soundManager;
    private final GamePlayerManager gameplayerManager;
    private final SceneManager sceneManager;

    // create constructor with entityManager, soundManager, gameplayerManager, sceneManager
    public CharacterEnemyHandler(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
        this.entityManager = entityManager;
        this.soundManager = soundManager;
        this.gameplayerManager = gameplayerManager;
        this.sceneManager = sceneManager;
    }

    // Handling for Character(Player) & Enemy Collision
    protected void characterEnemyCollision(iCollidable entityA, iCollidable entityB) {
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


}

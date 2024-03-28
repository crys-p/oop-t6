package com.mygdx.game.Collision;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.CollisionManager.iCollidable;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SoundManager.SoundEffectType;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.GameEntities.Collectible;
import com.mygdx.game.GameEntities.Enemy;
import com.mygdx.game.GameEntities.GameCharacter;
import com.mygdx.game.GameEntities.Wall;
import com.mygdx.game.player.GamePlayerManager;

import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;

public class CharacterEnemyHandler {

    private final SoundManager soundManager;
    private final GamePlayerManager gameplayerManager;

    public CharacterEnemyHandler(SoundManager soundManager, GamePlayerManager gameplayerManager) {
        this.soundManager = soundManager;
        this.gameplayerManager = gameplayerManager;
    }

    // Handling for Character(Player) & Enemy Collision
    protected void characterEnemyCollision(iCollidable entityA, iCollidable entityB) {
        Entity[] entities = downcastEntities(entityA, entityB);
        GameCharacter gameCharacter = (GameCharacter) entities[0];
        Enemy enemy = (Enemy) entities[1];

        // Wall Collision
        if (enemy instanceof Wall) {
            gameCharacter.moveBackwards();
        } else {
            enemy.respawn(0, MathUtils.random() * SCENE_HEIGHT);
            // Reduce health of player based on enemy damage
            gameplayerManager.takeDamage(gameCharacter, enemy);
            // Play hit sound
            soundManager.playSoundEffect(SoundEffectType.HIT);
        }
    }

    private Entity[] downcastEntities(iCollidable entityA, iCollidable entityB) {
        Enemy enemy;
        GameCharacter gameCharacter;

        if (entityA instanceof Enemy) {
            enemy = (Enemy) entityA;
            gameCharacter = (GameCharacter) entityB;
        } else {
            enemy = (Enemy) entityB;
            gameCharacter = (GameCharacter) entityA;
        }
        return new Entity[]{gameCharacter, enemy};
    }

}

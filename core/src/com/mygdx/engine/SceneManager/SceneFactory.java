package com.mygdx.engine.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.game.GameFactories.EntityFactoryManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.game.player.GamePlayerManager;
import com.mygdx.game.scenes.*;

public class SceneFactory implements iSceneFactory {

    @Override
    public Scene createScene(SceneManager.SceneType type, Game game, SceneManager sceneManager, EntityManager entityManager, EntityFactoryManager entityFactoryManager, IOManager ioManager, GamePlayerManager gameplayerManager, CameraManager cameraManager) {
        SpriteBatch batch = new SpriteBatch();
        ShapeRenderer shape = new ShapeRenderer();

        switch (type) {
            case START:
                return new StartScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case GAME:
                return new GameScene(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case GAMEL1:
                return new GameSceneL1(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case GAMEL2:
                return new GameSceneL2(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case MENU:
                return new MenuScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case VICTORY:
                return new VictoryScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case LOSE:
                return new LoseScene(game, sceneManager, entityManager, batch, shape, ioManager);
            default:
                throw new IllegalArgumentException("Unknown scene type: " + type);
        }
    }
}
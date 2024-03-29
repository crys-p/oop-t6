package com.mygdx.game.GameFactories;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;
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
            case GAMEL1:
                return new GameSceneL1(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case GAMEL2:
                return new GameSceneL2(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case GAMEL3:
                return new GameSceneL3(game, sceneManager, entityManager, entityFactoryManager, batch, shape, ioManager, gameplayerManager, cameraManager);
            case MENU:
                return new MenuScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case VICTORY:
                return new VictoryScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case LOSE:
                return new LoseScene(game, sceneManager, entityManager, batch, shape, ioManager);
            case INSTRUCT:
                return new InstructScene(game, sceneManager, entityManager, batch, shape, ioManager);
            default:
                throw new IllegalArgumentException("Unknown scene type: " + type);
        }
    }
}
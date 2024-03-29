package com.mygdx.game.GameFactories;

import com.badlogic.gdx.Game;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.game.GameFactories.EntityFactoryManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.game.player.GamePlayerManager;

public interface iSceneFactory {
     Scene createScene(SceneManager.SceneType type, Game game, SceneManager sceneManager, EntityManager entityManager, EntityFactoryManager entityFactoryManager, IOManager ioManager, GamePlayerManager gameplayerManager, CameraManager cameraManager);
}
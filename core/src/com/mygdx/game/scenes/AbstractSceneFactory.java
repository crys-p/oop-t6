package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;

public abstract class AbstractSceneFactory {
    // This method could be implemented if there's common setup needed for all scenes,
    // or it could remain abstract if its implementation is expected to differ among concrete factories.
    protected abstract void initializeCommon();

    // Abstract methods to create specific types of scenes. These must be implemented by concrete factories.
    public abstract Scene createStartScene(Game game, SceneManager sceneManager);
    public abstract Scene createGameScene(Game game, SceneManager sceneManager);
    public abstract Scene createMenuScene(Game game, SceneManager sceneManager);
    public abstract Scene createVictoryScene(Game game, SceneManager sceneManager);
    public abstract Scene createLoseScene(Game game, SceneManager sceneManager);
}

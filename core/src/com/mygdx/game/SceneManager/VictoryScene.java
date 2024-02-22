package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;

public class VictoryScene extends Scene {
    public VictoryScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.CYAN); // setting of background color for end scene
    }

    @Override
    protected void createEntities() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void render(float delta) {
        clearScreen();
    }
}

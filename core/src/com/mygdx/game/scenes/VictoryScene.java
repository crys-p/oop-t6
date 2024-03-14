package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;

public class VictoryScene extends Scene {
    protected TextButton createButtonNoIndex;

    public VictoryScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.CYAN); // setting of background color for end scene
    }

    protected void createButtonNoIndex() {
        createButtonNoIndex = ioManager.createButtonNoIndex("", IOManager.SCREEN_WIDTH / 2 - 250, IOManager.SCREEN_HEIGHT / 2 - 250, 500, 500, "victoryButtonStyle"); // Set the countdown duration to 10 seconds
    }

    @Override
    public void show() {
        //createEntities();
        createButtonNoIndex();
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
        batch.begin();
            createButtonNoIndex.draw(batch, 1);
        batch.end();
    }
}

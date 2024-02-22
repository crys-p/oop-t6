package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;

import java.util.Random;

public class StartScene extends Scene {

    public StartScene(Game game, EntityManager entityManager, SpriteBatch batch,ShapeRenderer shape, IOManager ioManager) {
        super(game, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.GRAY); // Set background color for start scene
        this.entityManager = entityManager;
    }

    @Override
    public void show() {
        createEntities();
    }

    @Override
    protected void createEntities() {
        // Create entities specific to the start scenex
        entityManager.createCharacter(1, 100, 0, 20, 0, null);
    }

    @Override
    public void hide() {
        // Logic when the start scene is hidden
    }

    @Override
    public void render(float delta) {
        // Clear the screen
        clearScreen();
        batch.begin();
        entityManager.drawAllEntities(batch);
        batch.end();
        // Update the camera and viewport
        //camera.update();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }
}
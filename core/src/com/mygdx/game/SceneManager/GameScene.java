package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.GameMaster;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.SimulationManager.SimulationManager;

import java.util.Random;

public class GameScene extends Scene {

    public GameScene(Game game, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, entityManager, batch, shape, ioManager);
        setBackgroundColor(Color.BLUE); // setting of background color for end scene
    }

    @Override
    public void show() {
        // Logic when the game scene is shown
        createEntities();
    }

    @Override
    public void createEntities() {
        entityManager.createCharacter(1, 100, 0, 400, 20, "UDLR");
        Random random = new Random();
        entityManager.createCollectibleRandom(10, random, 0, 20);
        entityManager.createEnemyRandomY(10, 300, random, 40, 40);
        entityManager.logAll(); // for debugging
    }


    @Override
    public void hide() {
        // Logic when the game scene is hidden
    }

    @Override
    public void render(float delta) {
        // Rendering logic for the game scene
        clearScreen();
        batch.begin();
        entityManager.drawAllEntities(batch);
        batch.end();
        //Gdx.gl.glClearColor(getBackgroundColor().r, getBackgroundColor().g, getBackgroundColor().b, getBackgroundColor().a);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }
}
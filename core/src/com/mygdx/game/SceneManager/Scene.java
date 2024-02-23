package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

//import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.EntityManager.EntityLifeCycle;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.IOManager.IOManager;

// implements Screen
// usage of ScreenAdapter over Screen as promote code reuse and maintains consistency
// accross screen and also without needing to apply all lifecycle Method
abstract class Scene extends ScreenAdapter {
    protected final Game game;
    protected Color backgroundColor; // Background color for the scene
    //protected OrthographicCamera camera;
    protected Viewport viewport;
    protected SpriteBatch batch;
    protected ShapeRenderer shape;
    protected EntityManager entityManager;
    protected IOManager ioManager;
    protected SceneManager sceneManager;

    // Set the desired viewport size
    private final int VIEWPORT_WIDTH = 1280;
    private final int VIEWPORT_HEIGHT = 720;

    private boolean paused = false;

    protected Scene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        this.game = game;
        this.backgroundColor = Color.BLACK; // Default background color
        this.entityManager = entityManager;
        this.batch = batch;
        this.shape = shape;
        this.ioManager = ioManager;
        this.sceneManager = sceneManager;
        // Create a camera and viewport
        //camera = new OrthographicCamera();
        //viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        //viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport.apply();
    }


    @Override
    public void show() {

    };

    @Override
    public void pause() {
        paused = true;
        // Additional logic to pause the scene
    }

    @Override
    public void resume() {
        paused = false;
        // Additional logic to resume the scene
    }

    protected boolean isPaused() {
        return paused;
    }

    // Abstract method to create entities specific to the scene
    protected abstract void createEntities();
    @Override
    public void resize(int width, int height) {
        Gdx.app.log("Scene", "Resizing scene to width: " + width + ", height: " + height);
        viewport.update(width, height);
        //camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        //camera.update();
    }

    @Override
    public abstract void hide();

    @Override
    public abstract void render(float delta);


    protected void clearScreen() {
        ScreenUtils.clear(getBackgroundColor().r, getBackgroundColor().g, getBackgroundColor().b, getBackgroundColor().a);
    }

    protected void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        Gdx.app.log("Scene", "Background color set to: " + color.toString());
    }

    protected Color getBackgroundColor() {
        return backgroundColor;
    }

}
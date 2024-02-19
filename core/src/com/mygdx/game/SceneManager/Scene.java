package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

//import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
// implements Screen
// usage of ScreenAdapter over Screen as promote code reuse and maintains consistency
// accross screen and also without needing to apply all lifecycle Method
public abstract class Scene extends ScreenAdapter {
    protected final Game game;
    protected Color backgroundColor; // Background color for the scene
    //protected OrthographicCamera camera;
    protected Viewport viewport;

    // Set the desired viewport size
    private final int VIEWPORT_WIDTH = 1280;
    private final int VIEWPORT_HEIGHT = 720;

    public Scene(Game game) {
        this.game = game;
        this.backgroundColor = Color.BLACK; // Default background color

        // Create a camera and viewport
        //camera = new OrthographicCamera();
        //viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, camera);
        //viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
        viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        viewport.apply();

    }

    @Override
    public abstract void show();
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

    // Other abstract methods for resize, pause, resume, and dispose if needed


    protected void clearScreen() {
        Gdx.gl.glClearColor(backgroundColor.r, backgroundColor.g, backgroundColor.b, backgroundColor.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        Gdx.app.log("Scene", "Background color set to: " + color.toString());
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

}

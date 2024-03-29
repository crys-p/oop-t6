package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Gdx;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;

import java.util.Random;


import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;
import com.badlogic.gdx.graphics.g2d.GlyphLayout; // Import GlyphLayout
import com.badlogic.gdx.utils.Align;

import com.badlogic.gdx.InputAdapter;
public class InstructScene extends Scene {
    private Stage stage;
    private BitmapFont font;
    private String instructionsText;
    private Texture backgroundTexture;
    protected TextButton countdownButton;;

    public InstructScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);


        stage = new Stage(new ScreenViewport(), batch);
        font = new BitmapFont(); // Uses default Arial font

        setupInstructions();

        backgroundTexture = new Texture(Gdx.files.internal("assets/gamescene.png"));
    }

    private void setupInstructions() {
        // Concatenate all level instructions into one string
        instructionsText = "Level 1: Avoid obstacles and collect coins!\n\n" +
                "Level 2: Watch out for enemies! Use power-ups wisely.\n\n" +
                "Level 3: Time is limited! Reach the end before it runs out.";
    }

    @Override
    protected void createEntities() {
        // Create entities specific to the start scene
    }

    protected void createCountdownButton() {
        countdownButton = ioManager.createCountdownButton("Start Countdown", 0, IOManager.SCREEN_WIDTH / 2 - 250, IOManager.SCREEN_HEIGHT / 2 - 300, 500, 500, "countdownButtonStyle", 10); // Set the countdown duration to 10 seconds

    }

    @Override
    public void render(float delta) {
        clearScreen();
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, viewport.getWorldWidth(), viewport.getWorldHeight());
        // Instructions for each level, potentially long and needing wrapping
        String level1Instructions = "Level 1: Avoid obstacles and collect Vegetable! Make sure to avoid those pesky unhealthy food.";
        String level2Instructions = "Level 2: Watch out for enemies! Collection 1000 point from the fruit to proceed to the next stage and remeber to avoid those unhealthy food";
        String level3Instructions = "Level 3: Time to find those very rare healthy food. avoid as many enemy as you can while find it and rmb a apple a day keep the doctor away. the Apple is to get your health back";

        // Maximum width for each instruction set to wrap text
        float maxWidthLevel1 = SCREEN_WIDTH / 3f - 40; // Adjust these values as needed
        float maxWidthLevel2 = SCREEN_WIDTH / 3f - 40;
        float maxWidthLevel3 = SCREEN_WIDTH / 3f - 40;

        // Prepare GlyphLayout with wrapping for each level's instructions
        GlyphLayout layout1 = new GlyphLayout(font, level1Instructions, Color.BLACK, maxWidthLevel1, Align.left, true);
        GlyphLayout layout2 = new GlyphLayout(font, level2Instructions, Color.BLACK, maxWidthLevel2, Align.center, true);
        GlyphLayout layout3 = new GlyphLayout(font, level3Instructions, Color.BLACK, maxWidthLevel3, Align.right, true);

        // Calculate X positions based on the alignment
        float xLevel1 = 20; // Left section with padding
        float xLevel2 = SCREEN_WIDTH / 3f + 20; // Middle section, adding offset
        float xLevel3 = SCREEN_WIDTH * 2f / 3f + 20; // Right section, adding offset

        // Starting Y position for the instructions
        float startY = SCREEN_HEIGHT - 250; // Adjust as necessary for padding from the top

        // Draw instructions for each level at specified positions
        font.draw(batch, layout1, xLevel1, startY);
        font.draw(batch, layout2, xLevel2, startY - layout1.height - 20); // Adjust Y based on previous layout height if needed
        font.draw(batch, layout3, xLevel3, startY - layout1.height - layout2.height - 40); // Adjust Y based on previous layouts height if needed

        countdownButton.draw(batch, 1);
        batch.end();

        // Process input events
        ioManager.processInput();
    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);
    }

    @Override
    public void show() {
        //createEntities();
        createCountdownButton();
        // After 10 seconds, switch to the GameSceneL1
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                Gdx.app.log("Timer", "Switching to GameSceneL1 after 5 seconds");
                sceneManager.showScene(SceneManager.SceneType.GAMEL1);
            }
        }, 10); // Delay of 10 seconds
    }

    @Override
    public void hide() {

    }
}
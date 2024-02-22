package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.GameMaster;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.IOManager.IOManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SimulationManager.SimulationManager;

import java.util.Random;

import static com.mygdx.game.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.game.IOManager.IOManager.SCREEN_WIDTH;

class GameScene extends Scene {

    private final int numberOfEnemy = 25;
    private final int numberOfCollectibles = 5;
    private PlayerControlManager playerControlManager;

    protected GameScene(Game game, SceneManager sceneManager, EntityManager entityManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager, PlayerControlManager playerControlManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        this.playerControlManager = playerControlManager;
        setBackgroundColor(Color.BLUE); // setting of background color for end scene
    }
    protected TextButton gameSceneButton;
    protected TextButton gameSceneButton1;

    @Override
    public void show() {
        // Logic when the game scene is shown
        createButtons();
        createEntities();
        playerControlManager.reset();
    }

    @Override
    protected void createEntities() {
        // Create enemy and collectible entities
        Random random = new Random();
        entityManager.createCollectibleRandom(numberOfCollectibles, random);
        entityManager.createEnemyRandom(numberOfEnemy, random);
        entityManager.logAll(); // for debugging

        // Create main player entity based on the number of players existing
        int totalPlayers = playerControlManager.getTotalNumberOfPlayers();
        int x = 0;
        for (int i = 0; i < totalPlayers; i++) {
            // If there are multiple players, set them 100px apart
            x += 100;
            entityManager.createCharacter(1, x, 0, 400, playerControlManager.getPlayerControls(i));
            playerControlManager.setPlayerControlledEntityID(i, entityManager.getLastEntityID());
        }
    }

    protected void createButtons() {
        // Define width and height for the buttons (you can adjust these values as needed)
        float buttonWidth = 200f;
        float buttonHeight = 100f;

        // Position the button at the top right corner
        float buttonX = SCREEN_WIDTH - buttonWidth - 20; // 20 is the padding from the right edge
        float buttonY = IOManager.SCREEN_HEIGHT - buttonHeight - 20; // 20 is the padding from the top edge

        // Position the button at the top right corner
        float soundbuttonX = SCREEN_WIDTH - buttonWidth - 10; // 20 is the padding from the right edge
        float soundbuttonY = SCREEN_HEIGHT - buttonHeight - 600; // 20 is the padding from the top edge

        // Create buttons using the IOManager
        gameSceneButton = ioManager.createButton("MENU", 3, buttonX, buttonY, buttonWidth, buttonHeight, "buttonGameStyle");
        gameSceneButton1 = ioManager.createButton("Toggle Sound", 4, soundbuttonX, soundbuttonY, buttonWidth, buttonHeight, "buttonGameStyle");



        // Add click listeners to the buttons
        gameSceneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 3
                int buttonIndex = 3; // Assuming button 1 is at index 3
                ioManager.handleButtonClick(buttonIndex);
            }
        });

        gameSceneButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 2
                int buttonIndex = 4; // Assuming button 2 is at index 1
                ioManager.handleButtonClick(buttonIndex);
            }
        });

        // Set button positions and add listeners if needed
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
        ioManager.displayPlayerInventory(batch);
        gameSceneButton.draw(batch, 1); // Adjust parameters as needed
        gameSceneButton1.draw(batch, 1); // Adjust parameters as needed
        batch.end();

        // This has to be rendered outside of normal batch as it requires shape which cannot overlap with SpriteBatch
        ioManager.displayPlayerHealth(new SpriteBatch(), new ShapeRenderer());

        // Process input events
        ioManager.processInput();

        if (playerControlManager.getNumDeadPlayers() > 0) {
            // Detect player death to call endscene
            this.sceneManager.showScene(SceneManager.SceneType.LOSE);
        }
        if (playerControlManager.getAllCollectibles() == numberOfCollectibles) {
            this.sceneManager.showScene(SceneManager.SceneType.VICTORY);
        }
        //Gdx.gl.glClearColor(getBackgroundColor().r, getBackgroundColor().g, getBackgroundColor().b, getBackgroundColor().a);
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        ioManager.updateMovement();

// Add click listeners to the buttons
        gameSceneButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 3
                int buttonIndex = 3; // Assuming button 1 is at index 3
                ioManager.handleButtonClick(buttonIndex);
            }
        });

        gameSceneButton1.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Handle click for button 2
                int buttonIndex = 4; // Assuming button 2 is at index 1
                ioManager.handleButtonClick(buttonIndex);
            }
        });


    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height);

    }

    @Override
    public void pause() {
        super.pause();
        // Additional logic to pause the game scene
    }

    @Override
    public void resume() {
        super.resume();
        // Additional logic to resume the game scene
    }


}
package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.engine.AIControlManager.AIControlManager;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameFactories.EntityFactoryManager;
import com.mygdx.game.GameFactories.NonPlayableEntityFactory;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.game.GameMovementStrategy.Player1Movement;
import com.mygdx.game.entities.EntityType;
import com.mygdx.game.player.GamePlayerManager;

import java.util.Random;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class GameSceneL1 extends Scene {

    private int numberOfEnemy = 1;
    private int numberOfCollectibles = 2;
    private GamePlayerManager gameplayerManager;
    private EntityFactoryManager entityFactoryManager;
    private CameraManager cameraManager;
    private SpriteBatch uiBatch;
    private static final int VIEWPORT_WIDTH = 1280;
    private static final int VIEWPORT_HEIGHT = 720;


    public GameSceneL1(Game game, SceneManager sceneManager, EntityManager entityManager, EntityFactoryManager entityFactoryManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager, GamePlayerManager gameplayerManager, CameraManager cameraManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        this.gameplayerManager = gameplayerManager;
        this.entityFactoryManager = entityFactoryManager;
        this.cameraManager = cameraManager;
        uiBatch = new SpriteBatch();
        setBackgroundColor(Color.PURPLE); // setting of background color for end scene

        // Assuming you are using a FitViewport for example
        //viewport = new FitViewport(VIEWPORT_WIDTH, VIEWPORT_HEIGHT, new OrthographicCamera());

    }
    protected TextButton gameSceneButton;
    protected TextButton gameSceneButton1;

    @Override
    public void show() {

        // Logic when the game scene is shown
        createButtons();
        createEntities();
        gameplayerManager.resetAllPlayerStats();
        // Apply the viewport
        Gdx.graphics.setWindowedMode(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
    }

    @Override
    protected void createEntities() {
        // Create entities based on the number of players existing
        int totalPlayers = gameplayerManager.getTotalNumberOfPlayers();
        numberOfCollectibles = numberOfCollectibles * totalPlayers;
        numberOfEnemy = numberOfEnemy * totalPlayers;

        // Create enemy and collectible entities based on number of players
        Random random = new Random();
        NonPlayableEntityFactory nonPlayableFactory = entityFactoryManager.getNonPlayable();

        // Retrieve AI movement objects from AIControlManager
        AIMovement lrmovement = AIControlManager.getLRMovement();
        AIMovement udmovement = AIControlManager.getUDMovement();

        nonPlayableFactory.create(EntityType.CUPCAKE.getId(), numberOfEnemy, random, 0, lrmovement);
        nonPlayableFactory.create(EntityType.DOUGHNUT.getId(), numberOfEnemy, random, 0, udmovement);
        nonPlayableFactory.create(EntityType.SUNDAE.getId(), numberOfEnemy, random, 0, udmovement);
        nonPlayableFactory.create(EntityType.APPLE.getId(), numberOfCollectibles, random, 0, lrmovement);
        nonPlayableFactory.create(EntityType.BANANA.getId(), numberOfCollectibles, random, 0, lrmovement);
        nonPlayableFactory.create(EntityType.WATERMELON.getId(), numberOfCollectibles, random, 0, lrmovement);

        // Create same amt of characters as players
        int x = 0;
        for (int i = 0; i < totalPlayers; i++) {
            // If there are multiple players, set them 100px apart
            x += 100;
            entityFactoryManager.getPlayable().create(EntityType.BOY.getId(), 1, x, 0, 400, new Player1Movement());
            gameplayerManager.setPlayerControlledEntityID(i, entityManager.getLastEntityID());
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
        viewport.apply(true);
        batch.begin();
        entityManager.drawAllEntities(batch);
        batch.end();
        uiBatch.begin();
        gameSceneButton.draw(uiBatch, 1); // Adjust parameters as needed
        gameSceneButton1.draw(uiBatch, 1); // Adjust parameters as needed
        uiBatch.end();

        // This is rendered separately as it requires both Shape and SpriteBatch which cannot overlap
        ioManager.displayPlayerInformation(new SpriteBatch(), new ShapeRenderer());
        // Process input events
        ioManager.processInput();

        if (gameplayerManager.getNumDeadPlayers() > 0) {
            // Detect player death to call end scene
            this.sceneManager.showScene(SceneManager.SceneType.LOSE);
        }
        if (gameplayerManager.getNumAllCollectibles() == numberOfCollectibles) {
            // Detect total collectibles to call victory scene
            this.sceneManager.showScene(SceneManager.SceneType.GAMEL2);
        }
        ioManager.updateMovement();

        //cameraManager.startCamera(delta, batch);

    }

    @Override
    public void resize(int width, int height) {
        // Update the viewport
        viewport.update(width, height, true);

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
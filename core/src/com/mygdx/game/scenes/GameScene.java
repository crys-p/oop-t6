package com.mygdx.game.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.engine.AIControlManager.AIMovement;
import com.mygdx.engine.CameraManager.Camera;
import com.mygdx.engine.CameraManager.CameraManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.GameFactory.EntityFactoryManager;
import com.mygdx.game.GameFactory.NonPlayableEntityFactory;
import com.mygdx.game.GameFactory.PlayableEntityFactory;
import com.mygdx.engine.IOManager.IOManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SceneManager.Scene;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.game.Player1Movement;
import com.mygdx.game.entities.EntityType;

import java.util.Random;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class GameScene extends Scene {

    private int numberOfEnemy = 10;
    private int numberOfCollectibles = 20;
    private PlayerManager playerManager;
    private EntityFactoryManager entityFactoryManager;
    private CameraManager cameraManager;

    public GameScene(Game game, SceneManager sceneManager, EntityManager entityManager, EntityFactoryManager entityFactoryManager, SpriteBatch batch, ShapeRenderer shape, IOManager ioManager, PlayerManager playerManager, CameraManager cameraManager) {
        super(game, sceneManager, entityManager, batch, shape, ioManager);
        this.playerManager = playerManager;
        this.entityFactoryManager = entityFactoryManager;
        this.cameraManager = cameraManager;
        setBackgroundColor(Color.BLUE); // setting of background color for end scene
    }
    protected TextButton gameSceneButton;
    protected TextButton gameSceneButton1;

    @Override
    public void show() {
        // Logic when the game scene is shown
        createButtons();
        createEntities();
        playerManager.resetAllPlayerStats();
    }

    @Override
    protected void createEntities() {
        // Create entities based on the number of players existing
        int totalPlayers = playerManager.getTotalNumberOfPlayers();
        numberOfCollectibles = numberOfCollectibles * totalPlayers;
        numberOfEnemy = numberOfEnemy * totalPlayers;

        // Create enemy and collectible entities based on number of players
        Random random = new Random();
        NonPlayableEntityFactory nonPlayableFactory = entityFactoryManager.getNonPlayableEntityFactory();
        // TODO: !!important - AI Movement Object should get from AIControlManager, e.g. AIControlManager.getLRMovement()
        nonPlayableFactory.create(EntityType.COLLECTIBLE, numberOfCollectibles, random, 0, new AIMovement(null));
        nonPlayableFactory.create(EntityType.ENEMY, numberOfEnemy, random, 0, new AIMovement(null));

        PlayableEntityFactory playableEntityFactory = entityFactoryManager.getPlayableEntityFactory();
        // Create same amt of characters as players
        int x = 0;
        for (int i = 0; i < totalPlayers; i++) {
            // If there are multiple players, set them 100px apart
            x += 100;
            playableEntityFactory.create(EntityType.CHARACTER, 1, x, 0, 400, new Player1Movement());
            playerManager.setPlayerControlledEntityID(i, entityManager.getLastEntityID());
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
            gameSceneButton.draw(batch, 1); // Adjust parameters as needed
            gameSceneButton1.draw(batch, 1); // Adjust parameters as needed
        batch.end();

        // This is rendered separately as it requires both Shape and SpriteBatch which cannot overlap
        ioManager.displayPlayerInformation(new SpriteBatch(), new ShapeRenderer());
        // Process input events
        ioManager.processInput();

        if (playerManager.getNumDeadPlayers() > 0) {
            // Detect player death to call end scene
            this.sceneManager.showScene(SceneManager.SceneType.LOSE);
        }
        if (playerManager.getNumAllCollectibles() == numberOfCollectibles) {
            // Detect total collectibles to call victory scene
            this.sceneManager.showScene(SceneManager.SceneType.VICTORY);
        }
        ioManager.updateMovement();

        cameraManager.startCamera(delta, batch);

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
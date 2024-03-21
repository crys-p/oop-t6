package com.mygdx.engine.CameraManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.player.GamePlayerManager;


public class CameraManager {
    private final Camera camera;
    private SimulationManager simulationManager;
    private GamePlayerManager gameplayerManager;
    private Vector3 position = new Vector3();


    public CameraManager(GamePlayerManager gameplayerManager) {
        this.camera = new Camera();
        this.gameplayerManager = gameplayerManager;
        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("CameraManager initialized"); // Log initialization message
    }

    public void startCamera(float delta, SpriteBatch batch) {
        camera.Camera(SCREEN_WIDTH, SCREEN_HEIGHT);

        float[] playerPosition = gameplayerManager.getPlayerPosition(0);
        try {
            position.x = playerPosition[0];
            position.y = playerPosition[1];
        } catch (Exception e) {
            System.out.println("Player has no entity");
        }

        camera.cameraUpdate(delta,position);
        batch.setProjectionMatrix(camera.camera.combined);
    }
}

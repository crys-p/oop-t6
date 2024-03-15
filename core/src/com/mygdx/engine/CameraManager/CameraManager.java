package com.mygdx.engine.CameraManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.CollisionManager.CollisionDetection;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.EntityManager.Entity;

import java.util.ArrayList;

public class CameraManager {
    private final Camera camera;
    private SimulationManager simulationManager;
    private PlayerManager playerManager;
    private Vector3 position = new Vector3();


    public CameraManager(PlayerManager playerManager) {
        this.camera = new Camera();
        this.playerManager = playerManager;
        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("CameraManager initialized"); // Log initialization message
    }

    public void startCamera(float delta, SpriteBatch batch) {
        //Entity player = getEntity(1);  //Need a way to get player x and y values
        camera.Camera(SCREEN_WIDTH, SCREEN_HEIGHT);
        float[] playerPosition = playerManager.getPlayerPosition(0);
        try {
            position.x = playerPosition[0];
            position.y = playerPosition[1];
        } catch (Exception e) {
            System.out.println("Player has no entity");
        }

        //camera.cameraUpdate(delta,position);
        batch.setProjectionMatrix(camera.camera.combined);
    }
}

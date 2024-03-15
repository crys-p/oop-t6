package com.mygdx.engine.AIControlManager;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.SimulationManager.SimulationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AIControlManager {
    private final Map<Entity, AIMovement> aiEntities;
    private static final Random random = new Random();
    private final SimulationManager simulationManager;

    public AIControlManager() {
        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("AIControlManager initialized"); // Log initialization message
        this.aiEntities = new HashMap<>();
    }
    public void update() {
        for (Map.Entry<Entity, AIMovement> entry : aiEntities.entrySet()) {
            AIMovement movement = entry.getValue();
            movement.update();
        }
    }
}
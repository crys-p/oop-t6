package com.mygdx.engine.AIControlManager;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.SimulationManager.SimulationManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class AIControlManager {
    private final Map<Entity, AIMovement> aiEntities;
    private static final Random random = new Random();
    private final SimulationManager simulationManager;

    private static AIMovement lrMovement;
    private static AIMovement udMovement;

    public AIControlManager() {
        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("AIControlManager initialized"); // Log initialization message
        this.aiEntities = new HashMap<>();
    }

    public static AIMovement getLRMovement() {
        if (lrMovement == null) {
            lrMovement = new LRMovement(null); // Pass appropriate entity or null depending on your design
        }
        return lrMovement;
    }

    public static AIMovement getUDMovement() {
        if (udMovement == null) {
            udMovement = new UDMovement(null); // Pass appropriate entity or null depending on your design
        }
        return udMovement;
    }

    public void update() {
        for (Map.Entry<Entity, AIMovement> entry : aiEntities.entrySet()) {
            AIMovement movement = entry.getValue();
            movement.update();
        }
    }
}

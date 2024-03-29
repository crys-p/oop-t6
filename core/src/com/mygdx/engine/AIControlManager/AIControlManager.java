package com.mygdx.engine.AIControlManager;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.MovementStrategy.AIMovement;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.AIMovementStrategy.LRMovement;
import com.mygdx.game.AIMovementStrategy.NoMovement;
import com.mygdx.game.AIMovementStrategy.UDMovement;

import java.util.HashMap;
import java.util.Map;

public class AIControlManager {
    private final Map<Entity, AIMovement> aiEntities;
    private final SimulationManager simulationManager;

    private static AIMovement lrMovement;
    private static AIMovement udMovement;
    private static AIMovement noMovement;

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

    public static AIMovement getNoMovement() {
        if (noMovement == null) {
            noMovement = new NoMovement(null); // Pass appropriate entity or null depending on your design
        }
        return noMovement;
    }

    public void update() {
        for (Map.Entry<Entity, AIMovement> entry : aiEntities.entrySet()) {
            AIMovement movement = entry.getValue();
            movement.update();
        }
    }
}

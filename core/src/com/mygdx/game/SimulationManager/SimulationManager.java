package com.mygdx.game.SimulationManager;

import java.util.logging.*;

public class SimulationManager {
    private static final Logger logger = Logger.getLogger(SimulationManager.class.getName());

    private static SimulationManager instance;

    private SimulationManager() {
        // initialization of the logic
        logger.info("SimulationManager initialized");
    }

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    public void logInfo(String message) {
        logger.info(message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logError(String message) {
        logger.severe(message);
    }
}

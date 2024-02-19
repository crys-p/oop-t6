package com.mygdx.game.SimulationManager;

import java.util.logging.*;

public class SimulationManager {
    private static final Logger logger = Logger.getLogger(SimulationManager.class.getName());

    static {
        logger.setLevel(Level.INFO); // Set logger level to INFO
    }

    private static SimulationManager instance;

    private SimulationManager() {
        // Initialization logic
        logger.info("SimulationManager initialized");
    }

    public static SimulationManager getInstance() {
        if (instance == null) {
            instance = new SimulationManager();
        }
        return instance;
    }

    public void logInfo(String message) {
        // 2 different way to show the log
        //logger.info(message);
        System.out.println("[INFO] " + message);
    }

    public void logWarning(String message) {
        logger.warning(message);
    }

    public void logError(String message) {
        logger.severe(message);
    }
}

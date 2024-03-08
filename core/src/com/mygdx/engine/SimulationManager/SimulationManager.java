package com.mygdx.engine.SimulationManager;

import java.io.File;
import java.util.logging.*;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
public class SimulationManager {
    private static final Logger logger = Logger.getLogger(SimulationManager.class.getName());

    static {
        try {
            // Storing of log files
            String logFolderPath = "errorlogs/";
            // will create folder when it does not exisit
            new File(logFolderPath).mkdirs();

            // creation with the desired log file name and path
            FileHandler fileHandler = new FileHandler(logFolderPath + "simulation.log");

            // set the log format
            fileHandler.setFormatter(new SimpleFormatter());

            // add FileHandler to the logger
            logger.addHandler(fileHandler);

            // set logger level to INFO
            //logger.setLevel(Level.WARNING);
            logger.setLevel(Level.INFO);
        } catch (IOException e) {
            // when there's an error creating the FileHandler, will log it
            logger.log(Level.SEVERE, "Error creating log file", e);


        }
    }

    private static SimulationManager instance;

    private SimulationManager() {

        logger.warning("SimulationManager initialized");

        // will intentionally throw an exception for testing error logging

        //throw new RuntimeException("There is error occurred during initialization");

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
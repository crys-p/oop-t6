package com.mygdx.game.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;
import com.mygdx.game.SimulationManager.SimulationManager;
import com.mygdx.game.SoundManager.SoundManager;


public class CollisionManager {

    private final CollisionHandler collisionHandler;
    private final CollisionDetection collisionDetection;
    private SimulationManager simulationManager;
    public CollisionManager(EntityManager entityManager, SoundManager soundManager, PlayerControlManager playerControlManager) {
        this.collisionDetection = new CollisionDetection(entityManager);
        this.collisionHandler = new CollisionHandler(entityManager, soundManager, playerControlManager);

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("CollisionManager initialized"); // Log initialization message
    }

    public void startCollisionDetection() {
        collisionDetection.setCollidables();
        collisionDetection.detectCollisions(this.collisionHandler);
    }

}
package com.mygdx.engine.CollisionManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.Collision.CollisionHandler;


public class CollisionManager {

    private final CollisionHandler collisionHandler;
    private final CollisionDetection collisionDetection;
    private SimulationManager simulationManager;
    public CollisionManager(EntityManager entityManager, SoundManager soundManager, PlayerManager playerManager) {
        this.collisionDetection = new CollisionDetection(entityManager);
        this.collisionHandler = new CollisionHandler(entityManager, soundManager, playerManager);

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("CollisionManager initialized"); // Log initialization message
    }

    public void startCollisionDetection() {
        collisionDetection.setCollidables();
        collisionDetection.detectCollisions(this.collisionHandler);
    }

}
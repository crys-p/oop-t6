package com.mygdx.engine.CollisionManager;
import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SceneManager.SceneManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.engine.SoundManager.SoundManager;
import com.mygdx.game.Collision.CollisionHandlers;
import com.mygdx.game.player.GamePlayerManager;


public class CollisionManager {

    private final CollisionHandlers collisionHandlers;
    private final CollisionDetection collisionDetection;
    private SimulationManager simulationManager;
    public CollisionManager(EntityManager entityManager, SoundManager soundManager, GamePlayerManager gameplayerManager, SceneManager sceneManager) {
        this.collisionDetection = new CollisionDetection(entityManager);
        this.collisionHandlers = new CollisionHandlers(entityManager, soundManager, gameplayerManager, sceneManager);

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("CollisionManager initialized"); // Log initialization message
    }

    public void startCollisionDetection() {
        collisionDetection.setCollidables();
        collisionDetection.detectCollisions(this.collisionHandlers);
    }

}
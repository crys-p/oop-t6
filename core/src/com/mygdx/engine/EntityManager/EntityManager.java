package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.GameEntities.Enemy;

import java.util.*;

public class EntityManager implements EntityLifeCycle {
    private List<Entity> entityList;
    private Map<Class<? extends Entity>, List<Entity>> entitySpecificMap;
    private Map<Integer, Entity> entityIDMap;
    private int entityCount;

    private SimulationManager simulationManager;

    // Constructor
    public EntityManager() {
        entityIDMap = new HashMap<>();
        entitySpecificMap = new HashMap<>();
        entityList = new ArrayList<>();

        simulationManager = SimulationManager.getInstance();
        simulationManager.logInfo("EntityManager initialized");
    }

    public void addEntity(Entity entity) {
        setUpEntityAttributes(entity);
    }

    @Override
    public void drawAllEntities(SpriteBatch batch) {
        for (Entity e : entityList) {
            e.draw(batch);
        }
    }

    @Override
    public void deleteAllEntities() {
        entityList.clear();
        entityIDMap.clear();
        for (Class cls : entitySpecificMap.keySet()) {
            entitySpecificMap.get(cls).clear();
        }
        entityCount = 0;
    }

    public int getLastEntityID() {
        return this.entityCount - 1;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR MOVEMENT/PLAYERMOVEMENT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public void movement() {
        for (Entity entity : entityList) {
            entity.movement();
            entity.updateBoundingBox();
        }
    }

    // For Player Controls
    public void inputMovement(int entityID, PlayerInstructions control) {
        if (entityIDMap.containsKey(entityID)) {
            entityIDMap.get(entityID).setPlayerInstructions(control);
        }
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR COLLISION MANAGER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public ArrayList<iCollidable> getCollidableEntities() {
        ArrayList<iCollidable> collidableEntities = new ArrayList<>();
        for (Entity entity: entityList) {
            if (entity instanceof iCollidable) {
                collidableEntities.add((iCollidable) entity);
            }
        }
        return collidableEntities;
    }


    public void removeEntity(Entity entity) {
        if (entity != null) {
            removeFromList(entity);
        }
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~INTERNAL/TESTING CODE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Set up Required Entity attributes
    private void setUpEntityAttributes(Entity entity) {
        // Set Entity ID and add to list
        int id = this.entityCount++;
        entity.setEntityID(id);
        addToList(entity, id);
    }

    // Add entity to correct entity list/groups
    private void addToList(Entity entity, int id) {
        // Add entity to entityList and ID Map
        entityList.add(entity);
        entityIDMap.put(id, entity);
        // Add entity to its entity specific list
        Class<? extends Entity> entityClass = entity.getClass();
        List<Entity> chosenList = entitySpecificMap.getOrDefault(entityClass, new ArrayList<>());
        entitySpecificMap.put(entityClass, chosenList);
        chosenList.add(entity);
    }

    private void removeFromList(Entity entity) {
        // Remove entity from entityList and ID Map and its entity specific list
        entityList.remove(entity);
        Class<? extends Entity> entityClass = entity.getClass();
        List<Entity> chosenList = entitySpecificMap.getOrDefault(entityClass, new ArrayList<>());
        chosenList.remove(entity);
    }

    public void logAll() {
        for (Entity e: entityList) {
            e.logConsole();
        }
    }

    public int getEntityID(Entity entity) {
        return entity.getEntityID();
    }

    public float[] getPosition(int entityID) {
        try {
            float x = entityIDMap.get(entityID).getX();
            float y = entityIDMap.get(entityID).getY();
            return new float[]{x,y};
        } catch (Exception e) {
            SimulationManager.getInstance().logError("Entity Manager: " + e);
        }
        return null;
    }
}
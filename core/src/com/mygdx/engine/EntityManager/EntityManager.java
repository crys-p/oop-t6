package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.Enemy;

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

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ENTITY LIFE CYCLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//    @Override
//    public void createCharacter(int quantity, float x, float y, float speed) {
//        for (int i = 0; i < quantity; i++) {
//            Character character = new Character(x, y, speed, "player.png");
//            this.setUpEntityAttributes(character);
//        }
//    }
//
//
//    public void createCharacter(int quantity, float x, float y, float speed, Player1Movement playerBehaviour) {
//        for (int i = 0; i < quantity; i++) {
//            Character character = new Character(x, y, speed, "player.png", playerBehaviour);
//            this.setUpEntityAttributes(character);
//        }
//    }
//
//    @Override
//    public void createCharacter(int quantity, Random random, float speed) {
//        for (int i = 0; i < quantity; i++) {
//            Character character = new Character(random.nextFloat() * SCREEN_WIDTH - 50, random.nextFloat() * SCREEN_HEIGHT - 50, speed, "player.png");
//            this.setUpEntityAttributes(character);
//        }
//    }
//
//    // Create enemies at specified positions
//    @Override
//    public void createEnemy(int quantity, float x, float y) {
//        for (int i = 0; i < quantity; i++) {
//            AIMovement movement = new AIMovement(null); // Initialize with null entity for now
//            Enemy enemy = new Enemy(x, y, "fire.png", movement);
//            movement.setEntity(enemy); // Set the entity for the AIMovement instance
//            this.setUpEntityAttributes(enemy);
//        }
//    }
//
//    // Create enemies at random positions
//    @Override
//    public void createEnemy(int quantity, Random random) {
//        for (int i = 0; i < quantity; i++) {
//            float randomX = random.nextFloat() * SCREEN_WIDTH;
//            float randomY = random.nextFloat() * SCREEN_HEIGHT - 50;
//            AIMovement movement = new AIMovement(null); // Initialize with null entity for now
//            Enemy enemy = new Enemy(randomX, randomY, "fire.png", movement);
//            movement.setEntity(enemy); // Set the entity for the AIMovement instance
//            this.setUpEntityAttributes(enemy);
//        }
//    }
//
//    @Override
//    public void createCollectible(int quantity, float x, float y) {
//        for (int i = 0; i < quantity; i++) {
//            AIMovement movement = new AIMovement(null); // Initialize with null entity for now
//            Collectible collectible = new Collectible(x, y, "star.png", movement);
//            movement.setEntity(collectible); // Set the entity for the AIMovement instance
//            this.setUpEntityAttributes(collectible);
//        }
//    }
//
//    @Override
//// Create collectibles at random positions
//    public void createCollectible(int quantity, Random random) {
//        for (int i = 0; i < quantity; i++) {
//            float randomX = random.nextFloat() * SCREEN_WIDTH - 50;
//            float randomY = random.nextFloat() * SCREEN_HEIGHT;
//            AIMovement movement = new AIMovement(null); // Initialize with null entity for now
//            Collectible collectible = new Collectible(randomX, randomY, "star.png", movement);
//            movement.setEntity(collectible); // Set the entity for the AIMovement instance
//            this.setUpEntityAttributes(collectible);
//        }
//    }


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
    public ArrayList<Entity> getCollidableEntities() {
        ArrayList<Entity> collidableEntities = new ArrayList<>();
        for (Entity entity: entityList) {
            if (entity instanceof iCollidable) {
                collidableEntities.add(entity);
            }
        }
        return collidableEntities;
    }


    public void removeEntity(Entity entity) {
        if (entity != null) {
            removeFromList(entity);
        }
    }

    public float getDamage(Enemy entity) {
        return entity.getDamage();
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

}
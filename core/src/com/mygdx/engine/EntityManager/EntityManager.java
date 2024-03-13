package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.Collectible;
import com.mygdx.game.entities.Enemy;
import com.mygdx.game.entities.Character;

import java.lang.reflect.Array;
import java.util.*;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

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
    @Override
    public void createCharacter(int quantity, float x, float y, float speed) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(x, y, speed, "player.png");
            this.setUpEntityAttributes(character);
        }
    }

    @Override
    public void createCharacter(int quantity, Random random, float speed) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(random.nextFloat() * SCREEN_WIDTH - 50, random.nextFloat() * SCREEN_HEIGHT - 50, speed, "player.png");
            this.setUpEntityAttributes(character);
        }
    }

    // Create enemies at specified positions
    @Override
    public void createEnemy(int quantity, float x, float y) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Enemy(x, y, "fire.png"));
        }
    }

    // Create enemies at random positions
    @Override
    public void createEnemy(int quantity, Random random) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Enemy(random.nextFloat() * SCREEN_WIDTH, random.nextFloat() * SCREEN_HEIGHT - 50, "fire.png"));
        }
    }

    @Override
    public void createCollectible(int quantity, float x, float y) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(x, y, "star.png"));
        }
    }

    @Override
    // Create collectibles at random positions
    public void createCollectible(int quantity, Random random) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(random.nextFloat() * SCREEN_WIDTH - 50, random.nextFloat() * SCREEN_HEIGHT, "star.png"));
        }
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
            entityIDMap.get(entityID).inputMovement(control);
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
        return entity.entityID;
    }

}
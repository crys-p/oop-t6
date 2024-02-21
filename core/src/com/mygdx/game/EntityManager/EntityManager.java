package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.*;

public class EntityManager implements EntityLifeCycle {
    private List<Entity> entityList;
    private List<Character> characterList;
    private List<Enemy> enemyList;
    private List<Collectible> collectibleList;
    private Map<Integer, Entity> entityIDMap;
    private int entityCount;

//    private boolean movingRight = true;
    // Constructor
    public EntityManager() {
        entityList = new ArrayList<>();
        characterList = new ArrayList<>();
        enemyList = new ArrayList<>();
        collectibleList = new ArrayList<>();
        entityIDMap = new HashMap<>();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~ENTITY LIFE CYCLE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void createCharacter(int quantity, float x, float y, float speed, float velocityY, String controls) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(x, y, speed, velocityY, "player.png", controls);
            this.setUpEntityAttributes(character);
        }
    }

    public void createEnemyRandomY(int quantity, float x, Random random, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Enemy(x, random.nextFloat() * 720, velocityX, velocityY, "knight.png"));
        }
    }

    @Override
    // Create item at random x positions
    public void createCollectibleRandom(int quantity, Random random, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(random.nextFloat() * 1280, random.nextFloat() * 720, velocityX, velocityY, "star.png"));
        } // 1280 as screen width --> use constant variable instead?
    }

    @Override
    // Create item at specific location
    public void createCollectible(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(x, y, velocityX, velocityY, "droplet.png"));
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
        entityCount = 0;
        characterList.clear();
        enemyList.clear();
        collectibleList.clear();
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR PlayerControl~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public int getPlayerEntityID() {
        // Iterate over the entityList
        for (Entity entity : entityList) {
            // Check if the entity is an instance of Character
            if (entity instanceof Character) {
                // If it is, return its entity ID
                return entity.getEntityID();
            }
        }
        // Return -1 if no player character is found
        return -1;
    }


    public void removeEntity(int entityID) {
        Entity entity = entityIDMap.get(entityID);
        if (entity != null) {
            removeFromList(entity);
        }
        System.out.println("No. of entities: " + entityList.size());
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR AI/IO MOVEMENT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public void movement() {
        for (Entity entity : entityList) {
            entity.movement();
            entity.updateBoundingBox();
        }
    }

    //updated so that io can call
    public void inputMovement(List<Integer> keys) {
        for (Character character : characterList) {
            for (int key : keys) {
                if (Objects.equals(character.inputControls, "UDLR") &&
                        (key == Input.Keys.LEFT || key == Input.Keys.RIGHT || key == Input.Keys.UP || key == Input.Keys.DOWN)) {
                    character.inputMovement(key);
                }
            }
        }
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR COLLISION MANAGER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public HashMap<Rectangle, Integer> getCharacterBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Character character: characterList) {
            boundingBoxes.put(character.boundingBox, character.entityID);
        }
        return boundingBoxes;
    }

    public HashMap<Rectangle, Integer> getEnemyBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Enemy enemy: enemyList) {
            boundingBoxes.put(enemy.boundingBox, enemy.entityID);
        }
        return boundingBoxes;
    }

    public HashMap<Rectangle, Integer> getCollectibleBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Collectible collectible: collectibleList) {
            boundingBoxes.put(collectible.boundingBox, collectible.entityID);
        }
        return boundingBoxes;
    }


    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~INTERNAL/TESTING CODE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Set up Required Entity attributes
    private void setUpEntityAttributes(Entity entity) {
        // Set Entity ID, create bounding box
        int id = this.entityCount++;
        entity.setEntityID(id);

        this.addToList(entity, id);
        this.createBoundingBox(entity);
    }

    // Add entity to correct entity list/groups
    private void addToList(Entity entity, int id) {
        // Add entity to entityList and ID Map
        entityList.add(entity);
        entityIDMap.put(id, entity);

        // Add entity to its respective list
        if (entity instanceof Character) {
            characterList.add((Character) entity);
        } else if (entity instanceof Enemy) {
            enemyList.add((Enemy) entity);
        } else if (entity instanceof Collectible) {
            collectibleList.add((Collectible) entity);
        }
    }

    private void removeFromList(Entity entity) {
        entityList.remove(entity);
        System.out.println("Removing entityID: " + entity.entityID);

        // Add entity to its respective list
        if (entity instanceof Character) {
            characterList.remove((Character) entity);
        } else if (entity instanceof Enemy) {
            enemyList.remove((Enemy) entity);
        } else if (entity instanceof Collectible) {
            collectibleList.remove((Collectible) entity);
        }
    }


    private void createBoundingBox(Entity e) {
        e.boundingBox = new Rectangle(e.x, e.y, e.getWidth(), e.getHeight());
    }

    public void logAll() {
        for (Entity e: entityList) {
            e.logConsole();
        }
    }

}
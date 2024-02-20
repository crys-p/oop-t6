package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.*;

public class EntityManager implements EntityCreation {
    private List<Entity> entityList;
    private List<Character> characterList;
    private List<Enemy> enemyList;
    private List<Item> itemList;
    private Map<Integer, Entity> entityIDMap;
    private int entityCount;

    // Constructor
    public EntityManager() {
        entityList = new ArrayList<>();
        characterList = new ArrayList<>();
        enemyList = new ArrayList<>();
        itemList = new ArrayList<>();
        entityIDMap = new HashMap<>();
    }

    @Override
    public void createCharacter(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(x, y, velocityX, velocityY, "player.png");
            this.addEntity(character);
            character.setInputControls("UDLR"); // probably has to be rewritten somewhere to set PlayerControl when user can choose
        }
    }

    @Override
    public void createCircle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float radius) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Circle(x, y, velocityX, velocityY, color, radius));
        }
    }

    @Override
    public void createTriangle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float sideLength) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Triangle(x, y, velocityX, velocityY, color, sideLength));
        }
    }

    @Override
    // Create item at random x positions
    public void createItemRandomX(int quantity, Random random, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Item(random.nextFloat() * 1280, y, velocityX, velocityY, "droplet.png"));
        } // 1280 as screen width --> use constant variable instead?
    }

    @Override
    // Create item at specific location
    public void createItem(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Item(x, y, velocityX, velocityY, "droplet.png"));
        }
    }


    public void drawEntities(SpriteBatch batch, ShapeRenderer shape) {
        for (Entity e : entityList) {
            if (e instanceof Character || e instanceof Item) {
                e.draw(batch);
            } else {
                e.draw(shape);
            }
        }
    }

    public void deleteAllEntities() {
        entityList.clear();
        entityIDMap.clear();
        entityCount = 0;
    }

    public void movement() {
        for (Entity entity : entityList) {
            entity.movement();
        }
    }

    // movement / inputmovement to confirm implementation again ..??
    public void movement(int entityID, int keyMovement) {
        Entity entity = this.entityIDMap.get(entityID);
        entity.inputMovement(keyMovement);
    }

    public void inputMovement(int key) {
        for (Character character : characterList) {
            if (Objects.equals(character.inputControls, "UDLR") &&
                    (key == Input.Keys.LEFT || key == Input.Keys.RIGHT || key == Input.Keys.UP || key == Input.Keys.DOWN)) {
                character.inputMovement(key);
            }
        }
    }

    // Add entity to respective lists
    private void addEntity(Entity entity) {
        // Add entity to list of all entities and set its ID
        entityList.add(entity);
        int id = this.entityCount++;
        entity.setEntityID(id);
        entityIDMap.put(id, entity);

        // Add entity to its respective list
        if (entity instanceof Character) {
            characterList.add((Character) entity);
        } else if (entity instanceof Enemy) {
            enemyList.add((Enemy) entity);
        } else if (entity instanceof Item) {
            itemList.add((Item) entity);
        }
    }

    public void logAll() {
        for (Entity e: entityList) {
            e.logConsole();
        }
    }

}
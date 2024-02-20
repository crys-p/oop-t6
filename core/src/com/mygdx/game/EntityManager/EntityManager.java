package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.CollisionManager.CollisionManager;

import java.util.*;

public class EntityManager implements EntityLifeCycle {
    private List<Entity> entityList;
    private List<Character> characterList;
    private List<Enemy> enemyList;
    private List<Collectible> collectibleList;
    private Map<Integer, Entity> entityIDMap;
    private int entityCount;

    // Constructor
    public EntityManager() {
        entityList = new ArrayList<>();
        characterList = new ArrayList<>();
        enemyList = new ArrayList<>();
        collectibleList = new ArrayList<>();
        entityIDMap = new HashMap<>();
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR SCENEMANAGER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    @Override
    public void createCharacter(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(x, y, velocityX, velocityY, "player.png");
            this.setUpEntityAttributes(character);
            character.setInputControls("UDLR"); // probably has to be rewritten somewhere to set PlayerControl when user can choose
        }
    }

    @Override
    public void createCircle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float radius) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Circle(x, y, velocityX, velocityY, color, radius));
        }
    }

    @Override
    public void createTriangle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float sideLength) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Triangle(x, y, velocityX, velocityY, color, sideLength));
        }
    }

    @Override
    // Create item at random x positions
    public void createItemRandomX(int quantity, Random random, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(random.nextFloat() * 1280, y, velocityX, velocityY, "droplet.png"));
        } // 1280 as screen width --> use constant variable instead?
    }

    @Override
    // Create item at specific location
    public void createItem(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            this.setUpEntityAttributes(new Collectible(x, y, velocityX, velocityY, "droplet.png"));
        }
    }

    @Override
    public void drawEntities(SpriteBatch batch, ShapeRenderer shape) {
        for (Entity e : entityList) {
            if (e instanceof Character || e instanceof Collectible) {
                e.draw(batch);
            } else {
                e.draw(shape);
            }
        }
    }

    @Override
    public void deleteAllEntities() {
        entityList.clear();
        entityIDMap.clear();
        entityCount = 0;
    }

    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR AI/IO MOVEMENT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //shermaine
    public List<Character> getCharacters() {
        return characterList;
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
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~FOR COLLISION MANAGER~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public HashMap<Rectangle, Integer> getCharacterBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Character character: characterList) {
            character.boundingBox = new Rectangle(character.x, character.y, character.getWidth(), character.getHeight());
            boundingBoxes.put(character.boundingBox, character.entityID);
        }
        return boundingBoxes;
    }

    public HashMap<Rectangle, Integer> getEnemyBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Enemy enemy: enemyList) {
            enemy.boundingBox = new Rectangle(enemy.x, enemy.y, enemy.getWidth(), enemy.getHeight());
            boundingBoxes.put(enemy.boundingBox, enemy.entityID);
        }
        return boundingBoxes;
    }

    public HashMap<Rectangle, Integer> getCollectibleBoundingBoxes() {
        HashMap<Rectangle, Integer> boundingBoxes = new HashMap<>();
        for (Collectible collectible: collectibleList) {
            collectible.boundingBox = new Rectangle(collectible.x, collectible.y, collectible.getWidth(), collectible.getHeight());
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

    public void logAll() {
        for (Entity e: entityList) {
            e.logConsole();
        }
    }

}
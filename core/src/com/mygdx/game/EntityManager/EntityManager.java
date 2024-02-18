package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.*;

public class EntityManager {
    private List<Entity> entityList = new ArrayList<>();
    private List<Character> characterList = new ArrayList<>();
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();
    private Map<Integer, Entity> entityIDMap = new HashMap<>();

    // Constructor
    public EntityManager() {

    }

    public void createEntities() {
        // FUNCTION TO ABANDON ONCE ENTITY CREATION IS SETUP IN SCENE!!!!
        // Creating Character
        Character character = new Character(-100, -100, 20, 0, "player.png");
        this.addEntity(character);
        character.setInputControls("UDLR");

        // Creating Items
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            this.addEntity(new Item(random, "droplet.png"));
        }
        // Creating Enemies
        this.addEntity(new Circle(200, 300, 400, 0, Color.RED, 50));
        this.addEntity(new Triangle(300, 200, 40, 40, Color.GREEN, 50));
    }

    public void createCharacter(int quantity, float x, float y, float velocityX, float velocityY) {
        for (int i = 0; i < quantity; i++) {
            Character character = new Character(x, y, velocityX, velocityY, "player.png");
            this.addEntity(character);
            character.setInputControls("UDLR"); // probably has to be rewritten somewhere to set PlayerControl when user can choose
        }
    }

    public void createCircle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float radius) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Circle(x, y, velocityX, velocityY, color, radius));
        }
    }

    public void createTriangle(int quantity, float x, float y, float velocityX, float velocityY, Color color, float sideLength) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Triangle(x, y, velocityX, velocityY, color, sideLength));
        }
    }

    // Create item at random
    public void createItem(int quantity, Random random) {
        for (int i = 0; i < quantity; i++) {
            this.addEntity(new Item(random, "droplet.png"));
        }
    }

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
    }

    public void entityMovement() {

    }


    public void setUpMovement() {
        for (Entity e : entityList) {
            e.movement(Gdx.graphics.getDeltaTime());
        }
    }

    public void inputMovement(int key) {
        for (Character character : characterList) {
            if (Objects.equals(character.inputControls, "UDLR") &&
                    (key == Input.Keys.LEFT || key == Input.Keys.RIGHT || key == Input.Keys.UP || key == Input.Keys.DOWN)) {
                character.inputMove(key);
            }
        }
    }

    // Add entity to respective lists
    private void addEntity(Entity entity) {
        // Add entity to list of all entities and set its ID
        entityList.add(entity);
        int id = entityIDMap.size();
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

}
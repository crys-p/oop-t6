package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EntityManager {
    private List<Entity> entityList = new ArrayList<>();
    private List<Character> characterList = new ArrayList<>();
    private List<Enemy> enemyList = new ArrayList<>();
    private List<Item> itemList = new ArrayList<>();

    // Constructor
    public EntityManager() {

    }

    public void createEntities() {
        // Creating Character
        this.addEntity(new Character(-100, -100, 20, 0, "player.png"));

        // Creating Items
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            this.addEntity(new Item(random, "droplet.png"));
        }
        // Creating Enemies
        this.addEntity(new Circle(200, 300, 400, 0, Color.RED, 50));
        this.addEntity(new Triangle(300, 200, 40, 40, Color.GREEN, 50));
    }

    // Add entity to respective lists
    private void addEntity(Character character) {
        entityList.add(character);
        characterList.add(character);
    }
    private void addEntity(Enemy enemy) {
        entityList.add(enemy);
        enemyList.add(enemy);
    }
    private void addEntity(Item item) {
        entityList.add(item);
        itemList.add(item);
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

    public void reduceHealth(Entity e, int amount) {

    }

    public void setUpMovement() {
        for (Entity e : entityList) {
            e.movement(Gdx.graphics.getDeltaTime());
        }
    }

    public List<Character> getPlayers() {
        return this.characterList;
    }
}
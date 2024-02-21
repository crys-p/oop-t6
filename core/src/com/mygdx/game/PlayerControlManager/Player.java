package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
    protected float x;
    protected float y;
    protected final Texture texture;
    protected int maxHealth = 100;
    protected int health;
    protected Inventory inventory;
    protected Vector2 position;

    protected Player(float x, float y, String image, Inventory inventory) {
        this.x = x;
        this.y = y;
        this.texture = new Texture(Gdx.files.internal(image));
        this.health = maxHealth;
        this.inventory = inventory;
    }
//
//    protected void heal(int amount) {
//        health = Math.min(health + amount, maxHealth);
//    }

    protected void move(Vector2 direction) {
        position.add(direction);
    }

    public void addItemToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    protected void removeItemFromInventory(Item item, int quantity) {
        inventory.removeItem(item, quantity);
    }

    public int getTotalItems() {
        return inventory.getTotalItems();
    }
    protected float getX() {
        return x;
    }
    protected float getY() {
        return y;
    }
    protected void setX(int x) {
        this.x = x;
    }
    protected void setY(int y) {
        this.y = y;
    }
    protected int getHealth() {
        return this.health;
    }
    protected void setHealth(int health) {
        this.health = health;
    }
    protected int getMaxHealth() {
        return this.maxHealth;
    }
    public Vector2 getPosition() {
        return position;
    }
}


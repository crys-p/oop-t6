package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player {
    protected int playerControlledEntityID;
    protected int maxHealth = 100;
    protected int health;
    protected Inventory inventory;
    private String keyControls;

    protected Player() {
        this.health = maxHealth;
        this.inventory = new Inventory();
    }
//
//    protected void heal(int amount) {
//        health = Math.min(health + amount, maxHealth);
//    }

    protected void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
    }
//    protected void move(Vector2 direction) {
//        position.add(direction);
//    }

    protected void addItemToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    protected void removeItemFromInventory(Item item, int quantity) {
        inventory.removeItem(item, quantity);
    }

    protected int getTotalItems() {
        return inventory.getTotalItems();
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
//    public Vector2 getPosition() {
//        return position;
//    }

    public void setPlayerKeyControls(String playerControl) {
        this.keyControls = playerControl;
    }
}


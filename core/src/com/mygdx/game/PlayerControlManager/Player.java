package com.mygdx.game.PlayerControlManager;

public class Player {
    private int playerControlledEntityID;
    private int maxHealth = 100;
    private int health;
    private float speed;
    protected Inventory inventory;
    private String keyControls = "UDLR"; // default key controls if not set

    protected Player(Inventory inventory) {
        this.health = maxHealth;
        this.inventory = inventory;
        this.speed = 400;
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

    public void addItemToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    protected void removeItemFromInventory(Item item, int quantity) {
        inventory.removeItem(item, quantity);
    }

    public int getTotalItems() {
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

    public String getPlayerKeyControls() {
        return this.keyControls;
    }

    protected int getPlayerControlledEntityID() {
        return this.playerControlledEntityID;
    }

    protected float getSpeed() {
        return this.speed;
    }

    protected void setSpeed(float speed) {
        this.speed = speed;
    }
}


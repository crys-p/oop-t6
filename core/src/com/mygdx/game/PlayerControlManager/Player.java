package com.mygdx.game.PlayerControlManager;

public class Player {
    private int playerControlledEntityID = -1;
    private final int maxHealth = 100;
    private int health;
    protected Inventory inventory;
    private String keyControls = "UDLR"; // default key controls if not set

    protected Player() {
        this.health = maxHealth;
        this.inventory = new Inventory();
    }
    //not in used yet
//    protected void heal(int amount) {
//        health = Math.min(health + amount, maxHealth);
//    }

    protected void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
    }
//    protected void move(Vector2 direction) {
//        position.add(direction);
//    }

    public void addToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    //not in used yet
//    protected void removeItemFromInventory(Item item, int quantity) {
//        inventory.removeItem(item, quantity);
//    }
//
//    public int getTotalItems() {
//        return inventory.getTotalItems();
//    }

    protected int getHealth() {
        return this.health;
    }
    protected void setHealth(int health) {
        this.health = health;
    }
    protected int getMaxHealth() {
        return this.maxHealth;
    }
    public void setPlayerKeyControls(String playerControl) {
        this.keyControls = playerControl;
    }

    public String getPlayerKeyControls() {
        return this.keyControls;
    }

    protected int getPlayerControlledEntityID() {
        return this.playerControlledEntityID;
    }

    // Getter for the inventory
    protected int getInventoryCount() {
        return inventory.getTotalItems();
    }
}

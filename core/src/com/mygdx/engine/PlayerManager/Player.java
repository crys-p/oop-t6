package com.mygdx.engine.PlayerManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlConfigs;

class Player {
    private int playerControlledEntityID = -1;
    private final int maxHealth = 100;
    private int health;
    private PlayerController playerController;
    protected Inventory inventory;

    protected Player() {
        this.health = maxHealth;
        this.inventory = new Inventory();
        this.playerController = new PlayerController();
    }

    //not in used yet
//    protected void heal(int amount) {
//        health = Math.min(health + amount, maxHealth);
//    }

    protected void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
    }


    protected void addToInventory(Item item, int quantity) {
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
    protected void setPlayerKeyConfigs(PlayerControlConfigs control) {
        this.playerController.setKeyConfigs(control);
    }

    protected PlayerControlConfigs getPlayerKeyConfigs() {
        return this.playerController.getKeyConfigs();
    }

    protected int getPlayerControlledEntityID() {
        return this.playerControlledEntityID;
    }

    // Getter for the inventory
    protected int getInventoryCount() {
        return inventory.getTotalItems();
    }

    protected void clearInventory() {
        inventory.clear();
    }

    // Moves the player if the key is part of player's control
    protected void move(Integer key, EntityManager entityManager) {
        PlayerInstructions instr = this.playerController.getPlayerMovement(key);
        if (instr != null) {
            entityManager.inputMovement(this.playerControlledEntityID, instr);
        }
    }
}

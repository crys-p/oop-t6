package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class PlayerControlManager{
    private int health;
    private int maxHealth;
    private float movementSpeed;
    private float jump;
    private CollisionManager collisionManager;
    private boolean onGround;
    private final List<Item> inventory;
    private final EntityManager entityManager;

    // Constructor to initialize EntityManager
    public PlayerControlManager(EntityManager entityManager) {
//        super();
        this.entityManager = entityManager;
        this.inventory = new ArrayList<>(); // Initialize the inventory list
    }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public float getMovementSpeed() { return movementSpeed; }
    public void setMovementSpeed(float movementSpeed) { this.movementSpeed = movementSpeed; }
    public float getJump() { return jump; }
    public void setJump(float jump) { this.jump = jump; }
    // Method to add an item to the inventory
    public void addItemToInventory(Item item) {
        inventory.add(item);
    }
    // Method to remove an item from the inventory
    public void removeItemFromInventory(Item item) {
        inventory.remove(item);
    }
    // Method to get the inventory
    public List<Item> getInventory() {
        return inventory;
    }
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    public boolean isOnGround() { return onGround; }

}

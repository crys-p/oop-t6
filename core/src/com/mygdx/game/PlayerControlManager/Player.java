package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.CollisionManager.CollisionManager;

import java.util.ArrayList;

public class Player {
    protected int playerEntityID;
    protected int health;
    protected int maxHealth = 100;
    protected final ArrayList<String> inventory;
    protected float movementSpeed;
    protected float jump;
    protected boolean onGround;

    // Constructor
    protected Player() {
        this.health = 100;
        this.inventory = new ArrayList<>();
    }

    // Getter for health
    protected int getHealth() {
        return health;
    }

    // Setter for health
    protected void setHealth(int health) {
        this.health = Math.min(health, maxHealth); // Ensure health doesn't exceed maxHealth
    }
    protected int getMaxHealth() { return maxHealth; }

    // Method to add an item to the inventory
    protected void addItemToInventory(String item) {
        inventory.add(item);
    }

    // Method to remove an item from the inventory
    protected void removeItemFromInventory(String item) {
        inventory.remove(item);
    }

    // Method to print the player's inventory
    public void printInventory() {
        System.out.println("Player's Inventory:");
        for (String item : inventory) {
            System.out.println(item);
        }
    }

    // Method to get the total number of items in the inventory
    protected int getTotalItems() {
        return inventory.size();
    }
    protected float getMovementSpeed() { return movementSpeed; }
    protected void setMovementSpeed(float movementSpeed) { this.movementSpeed = movementSpeed; }
    protected float getJump() { return jump; }
    protected void setJump(float jump) { this.jump = jump; }
    protected void setOnGround(boolean onGround) { this.onGround = onGround; }
    protected boolean isOnGround() { return onGround; }

//    // Example main method to test the Player class
//    public void main(String[] args) {
//        // Create a new player with initial health of 100
//        Player player = new Player(100);
//
//        // Add some items to the inventory
//        player.addItemToInventory("Sword");
//        player.addItemToInventory("Potion");
//        player.addItemToInventory("Shield");
//
//        // Print the player's inventory
//        player.printInventory();
//
//        // Print the total number of items in the inventory
//        System.out.println("Total Items: " + player.getTotalItems());
//
//        // Set the player's health to 80
//        player.setHealth(80);
//
//        // Print the player's health
//        System.out.println("Player's Health: " + player.getHealth());
//    }
}

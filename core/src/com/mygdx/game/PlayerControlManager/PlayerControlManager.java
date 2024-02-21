// PlayerControlManager.java
package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.EntityManager.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PlayerControlManager {
    protected final ArrayList<Player> allPlayers;
    private final Map<Player, Integer> playerEntityMap; // Map to store playerEntityID and corresponding Player instances
    private final EntityManager entityManager; // Assume you have a reference to EntityManager
    private final Inventory inventory; // Add the inventory field

    public PlayerControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.inventory = new Inventory(this);
        playerEntityMap = new HashMap<>();
        allPlayers = new ArrayList<>();
    }

    // Method to create a players
    public void createPlayers(int qty) {
        for (int i = 0; i < qty; i++) {
            Player player = new Player(this.inventory);
            allPlayers.add(player);
        }
    }

    // Set player Entity ID when entity is created

    public int getPlayerEntityID() {
        return entityManager.getPlayerEntityID();
    }


    // Method to remove a player from the manager
    public void removePlayer(int index) {
        allPlayers.remove(index);
    }

    // Method to get entity ID from player instance
    public void setPlayerControlledEntityID(Player player, int entityID) {
        playerEntityMap.put(player, entityID);
    }

    // Method to get entity ID from player instance
    public Integer getPlayerControlledEntityID(Player player) {
        return playerEntityMap.get(player);
    }

    // Method to handle taking damage
// Method to handle taking damage
    public void takeDamage(int entityID, int damage) {
        // Loop through all players and apply damage
//        for (Player player : allPlayers) {
//            if (playerEntityMap.get(player) == entityID){
//                // Calculate the new health after taking damage
//                int newHealth = player.getHealth() - damage;
//
//                // Ensure health never goes below 0
//                if (newHealth < 0) {
//                    newHealth = 0;
//                }
//                // Update the player's health
//                player.setHealth(newHealth);
//            }
//        }
    }

    public void setPlayerControl(int playerNumber, String playerControl) {
        try {
            Player player = allPlayers.get(playerNumber);
            player.setPlayerKeyControls(playerControl);
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("Error: Player does not exist.");
        }
    }
    // Getter for the inventory
    public Inventory getInventory() {
        return inventory;
    }
}

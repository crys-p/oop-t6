// PlayerControlManager.java
package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.EntityManager.EntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerControlManager {
    protected final ArrayList<Player> allPlayers;
    private final Map<Player, Integer> playerEntityMap; // Map to store Player instances and corresponding entity being controlled
    private final EntityManager entityManager; // Assume you have a reference to EntityManager
    private final Inventory inventory; // Add the inventory field

    public PlayerControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.inventory = new Inventory(this);
        playerEntityMap = new HashMap<>();
        allPlayers = new ArrayList<>();
    }

    // Method to create players
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
    public void setPlayerControlledEntityID(int playerNumber, int entityID) {
        try {
            Player player = allPlayers.get(playerNumber);
            playerEntityMap.put(player, entityID);
        } catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    // Method to get entity ID from player instance
    public int getPlayerControlledEntityID(Player player) {
        return playerEntityMap.get(player);
    }

    // Method to handle taking damage
    public void takeDamage(int characterID, int enemyID) {
        float damage = entityManager.getDamage(enemyID);
        // Loop through all players and apply damage
        for (Player player : allPlayers) {
            if (playerEntityMap.get(player) == characterID) {
                // Calculate the new health after taking damage
                int newHealth = player.getHealth() - (int) damage;

                // Ensure health never goes below 0
                if (newHealth < 0) {
                    newHealth = 0;
                }
                // Update the player's health
                player.setHealth(newHealth);
            }
        }
    }

    // Getter for the inventory
    public Inventory getInventory() {
        return inventory;
    }

    public int getTotalNumberOfPlayers() {
        return allPlayers.size();
    }

    public void setPlayerControl(int playerNumber, String playerControl) {
        try {
            Player player = allPlayers.get(playerNumber);
            player.setPlayerKeyControls(playerControl);
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("Error: Player does not exist.");
        }
    }

    public String getPlayerControls(int playerNumber) {
        return allPlayers.get(playerNumber).getPlayerKeyControls();
    }

    public int getPlayerHealth(int playerNumber) {
        return allPlayers.get(playerNumber).getHealth();
    }


    public HashMap<Integer, List<Integer>> getAllPlayerHealthStats() {
        HashMap<Integer, List<Integer>> allPlayerHealthStats = new HashMap<>();
        int counter = 0;
        for (Player player : allPlayers) {
            allPlayerHealthStats.put(counter, new ArrayList<Integer>());
            allPlayerHealthStats.get(counter).add(player.getHealth());
            allPlayerHealthStats.get(counter).add(player.getMaxHealth());
            counter++;
        }
        return allPlayerHealthStats;
    }
}

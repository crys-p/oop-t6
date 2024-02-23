// PlayerControlManager.java
package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Input;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.*;

public class PlayerControlManager {
    private final ArrayList<Player> allPlayers;
    private final Map<Player, Integer> playerEntityMap; // Map to store Player instances and corresponding entity being controlled
    private final HashMap<String, List<Integer>> keyMaps =  new HashMap<>();
    private int numDeadPlayers = 0;
    private int allPlayerInventoryCount = 0;
    private final EntityManager entityManager; // Assume you have a reference to EntityManager
    public PlayerControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        playerEntityMap = new HashMap<>();
        allPlayers = new ArrayList<>();
        this.setKeyMaps();
    }

    // Method to create players
    public void createPlayers(int qty) {
        for (int i = 0; i < qty; i++) {
            Player player = new Player();
            allPlayers.add(player);
        }
    }

    // Method to remove a player from the manager
    public void removePlayer(int index) {
        allPlayers.remove(index);
    }

    // Method to get entity ID from player instance
    public void setPlayerControlledEntityID(int playerNumber, int entityID) {
        Player player = allPlayers.get(playerNumber);
        playerEntityMap.put(player, entityID);
        player.setPlayerControlledEntityID(entityID);
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
                if (newHealth <= 0) {
                    numDeadPlayers++;
                    newHealth = 0;
                }
                // Update the player's health
                player.setHealth(newHealth);
            }
        }
    }

    public void addItemToInventory(int characterID) {
        for (Player player: allPlayers) {
             if (playerEntityMap.get(player) == characterID) {
                 player.addToInventory(new Item(), 1);
                 allPlayerInventoryCount++;
             }
        }
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

    public List<Integer> getAllPlayerInventory() {
        List <Integer> allInventory = new ArrayList<>();
        for (Player player : allPlayers) {
            allInventory.add(player.getInventoryCount());
        }
        return allInventory;
    }

    public void handlePressedKeys(List<Integer> keys) {
        // For each key pressed
        for (Integer key: keys) {
            for (Player player : allPlayers) {
                // Check each player's key config to see if it matches
                String keyConfigs = player.getPlayerKeyControls();
                if (keyMaps.get(keyConfigs).contains(key)) {
                    // If player entity id exists, send instruction to player
                    int playerEntityID = player.getPlayerControlledEntityID();
                    if (playerEntityID != -1) {
                        PlayerInstructions instr = getDirectionFromKey(key);
                        entityManager.inputMovement(player.getPlayerControlledEntityID(), instr);
                    }
                }
            }
        }
    }

    private PlayerInstructions getDirectionFromKey(int key) {
            if (key == Input.Keys.LEFT || key == Input.Keys.A) {
                return PlayerInstructions.LEFT;
            } else if (key == Input.Keys.RIGHT || key == Input.Keys.D) {
                return PlayerInstructions.RIGHT;
            } else if (key == Input.Keys.UP || key == Input.Keys.W) {
                return PlayerInstructions.UP;
            } else if (key == Input.Keys.DOWN || key == Input.Keys.S) {
                return PlayerInstructions.DOWN;
            }
        return null; // No direction found for the given key in the control configuration
    }

    private void setKeyMaps() {
        keyMaps.put("UDLR", Arrays.asList(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN));
        keyMaps.put("WASD", Arrays.asList(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S));
    }


    public int getNumDeadPlayers() {
        return numDeadPlayers;
    }


    // method to reset damage taken by players
    public void resetAllPlayerStats() {
        // loop through all players and reset their health to maximum
        for (Player player : allPlayers) {
            player.setHealth(player.getMaxHealth());
            player.clearInventory();
        }
        numDeadPlayers = 0;
        allPlayerInventoryCount = 0;
    }


    public int getNumAllCollectibles() {
        return allPlayerInventoryCount;
    }

}

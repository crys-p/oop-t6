// PlayerManager.java
package com.mygdx.game.player;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.PlayerManager.Item;
import com.mygdx.engine.PlayerManager.PlayerManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.PlayerControlConfigs;
import com.mygdx.game.entities.Enemy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlayerManager extends PlayerManager {
    private final ArrayList<GamePlayer> allPlayers;
    private final Map<GamePlayer, Integer> playerEntityMap; // Map to store GamePlayer instances and corresponding entity being controlled
    private int numDeadPlayers = 0;
    private int allPlayerInventoryCount = 0;
    private SimulationManager simulationManager;
    public GamePlayerManager(EntityManager entityManager) {
        super(entityManager);
        playerEntityMap = new HashMap<>();
        allPlayers = new ArrayList<>();

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("PlayerManager initialized"); // Log initialization message
    }

    // Method to create players
    public void createPlayers(int qty) {
        for (int i = 0; i < qty; i++) {
            GamePlayer player = new GamePlayer();
            allPlayers.add(player);
        }
    }

    // Method to remove a player from the manager
    public void removePlayer(int index) {
        allPlayers.remove(index);
    }

    // Method to get entity ID from player instance
    public void setPlayerControlledEntityID(int playerNumber, int entityID) {
        GamePlayer player = allPlayers.get(playerNumber);
        playerEntityMap.put(player, entityID);
        player.setPlayerControlledEntityID(entityID);
    }

    public void handlePressedKeys(List<Integer> keys) {
        // For each key pressed
        for (Integer key: keys) {
            for (GamePlayer player : allPlayers) {
                // Check if current player is controlling any entity
                if (player.getPlayerControlledEntityID() != -1)
                {
                    // Send this key for to player to handle
                    player.move(key, entityManager);
                }
            }
        }
    }

    // Method to handle taking damage
    public void takeDamage(PlayableCharacter playableCharacter, Enemy enemy) {
        int characterID = entityManager.getEntityID(playableCharacter);
        float damage = entityManager.getDamage(enemy);
        // Loop through all players and apply damage
        for (GamePlayer player : allPlayers) {
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
        for (GamePlayer player: allPlayers) {
             if (playerEntityMap.get(player) == characterID) {
                 player.addToInventory(new Item(), 1);
                 allPlayerInventoryCount++;
             }
        }
    }

    public int getTotalNumberOfPlayers() {
        return allPlayers.size();
    }

    public void setPlayerControl(int playerNumber, PlayerControlConfigs playerControl) {
        try {
            GamePlayer player = allPlayers.get(playerNumber);
            player.setPlayerKeyConfigs(playerControl);
        } catch (Exception IndexOutOfBoundsException) {
            System.out.println("Error: GamePlayer does not exist.");
        }
    }

    public PlayerControlConfigs getPlayerControls(int playerNumber) {
        return allPlayers.get(playerNumber).getPlayerKeyConfigs();
    }


    public HashMap<Integer, List<Integer>> getAllPlayerHealthStats() {
        HashMap<Integer, List<Integer>> allPlayerHealthStats = new HashMap<>();
        int counter = 0;
        for (GamePlayer player : allPlayers) {
            allPlayerHealthStats.put(counter, new ArrayList<Integer>());
            allPlayerHealthStats.get(counter).add(player.getHealth());
            allPlayerHealthStats.get(counter).add(player.getMaxHealth());
            counter++;
        }
        return allPlayerHealthStats;
    }

    public List<Integer> getAllPlayerInventory() {
        List <Integer> allInventory = new ArrayList<>();
        for (GamePlayer player : allPlayers) {
            allInventory.add(player.getInventoryCount());
        }
        return allInventory;
    }


    public int getNumDeadPlayers() {
        return numDeadPlayers;
    }


    // method to reset damage taken by players
    public void resetAllPlayerStats() {
        // loop through all players and reset their health to maximum
        for (GamePlayer player : allPlayers) {
            player.setHealth(player.getMaxHealth());
            player.clearInventory();
        }
        numDeadPlayers = 0;
        allPlayerInventoryCount = 0;
    }


    public int getNumAllCollectibles() {
        return allPlayerInventoryCount;
    }

    public float[] getPlayerPosition(int playerNumber) {
        int entityID = allPlayers.get(playerNumber).getPlayerControlledEntityID();
        if (entityID != -1) {
            return entityManager.getPosition(entityID);
        } else {
            return null;
        }
    }
}

// PlayerControlManager.java
package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.EntityManager.EntityManager;

import java.util.HashMap;
import java.util.Map;

public class PlayerControlManager {
    private final Map<Integer, Player> players; // Map to store playerEntityID and corresponding Player instances
    private final EntityManager entityManager; // Assume you have a reference to EntityManager

    public PlayerControlManager(EntityManager entityManager) {
        players = new HashMap<>();
        this.entityManager = entityManager;
    }

    // Method to create a player
    public void createPlayer(float x, float y) {
        Player player = new Player(x, y, "player.png");
        addPlayer(player); // Add the created player to the manager
    }

    public int getPlayerEntityID() {
        return entityManager.getPlayerEntityID();
    }

    // Method to add a player to the manager
    public void addPlayer(Player player) {
        int playerEntityID = entityManager.getPlayerEntityID();
        players.put(playerEntityID, player);
    }
    // Method to remove a player from the manager
    public void removePlayer(int playerEntityID) {
        players.remove(playerEntityID);
    }

    // Method to get the player instance by entity ID
    public Player getPlayer(int playerEntityID) {
        return players.get(playerEntityID);
    }

    // Method to handle taking damage
// Method to handle taking damage
    public void takeDamage(int damage) {
        // Loop through all players and apply damage
        for (Player player : players.values()) {
            // Calculate the new health after taking damage
            int newHealth = player.getHealth() - damage;

            // Ensure health never goes below 0
            if (newHealth < 0) {
                newHealth = 0;
            }

            // Update the player's health
            player.setHealth(newHealth);

        }
    }

}

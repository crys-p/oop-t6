package com.mygdx.game.PlayerControlManager;

import java.util.HashMap;
import java.util.Map;

public class PlayerControlManager {
    private Map<Integer, Player> players; // Map to store playerEntityID and corresponding Player instances

    public PlayerControlManager() {
        players = new HashMap<>();
    }

    // Method to add a player to the manager
    public void addPlayer(int playerEntityID, Player player) {
        players.put(playerEntityID, player);
    }

    // Method to get the player instance by entity ID
    public Player getPlayer(int playerEntityID) {
        return players.get(playerEntityID);
    }
    // Method to deduct health from a player using playerEntityID
//    public void takeDamage(int damageAmount) {
//        int currentHealth = player.getHealth();
//        currentHealth -= damageAmount;
//        player.setHealth(currentHealth);
//        if (currentHealth <= 0) {
//            // Player has died, handle accordingly (e.g., game over)
//            // You might want to have another method to handle player death
//            handlePlayerDeath(player.playerEntityID);;
//        }
//    }
    // Method to handle player death
    public void handlePlayerDeath(int playerEntityID) {
        // Handle player death logic here
        System.out.println("Player with entity ID " + playerEntityID + " has died.");
    }
}

package com.mygdx.game.IO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.GameEntities.EntityType;
import com.mygdx.game.player.GamePlayer;
import com.mygdx.game.player.GamePlayerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class InventoryDisplay {

    private GamePlayerManager gameplayerManager;
    protected final BitmapFont font;

    public InventoryDisplay(GamePlayerManager gameplayerManager) {
        this.gameplayerManager = gameplayerManager;
        this.font = new BitmapFont();

//        // Initialize inventory with counts starting from 0 for each vegetable type
//        for (int playerId = 0; playerId < gameplayerManager.getTotalNumberOfPlayers(); playerId++) {
//            Map<EntityType, Integer> inventory = new HashMap<>();
//            for (EntityType type : EntityType.values()) {
//                inventory.put(type, 0); // Start count for each vegetable type at 0
//            }
//            gameplayerManager.setCharacterInventory(playerId, inventory);
//        }
    }

    public void render(SpriteBatch batch) {
        List<Map<EntityType, Integer>> allPlayerInventory = gameplayerManager.getAllPlayerInventory();
        float posX = SCREEN_WIDTH * 0.01f + 250; // Distance from the left edge of the screen
        float posY = SCREEN_HEIGHT * 0.9f - 10; // Distance from the top edge of the screen

        for (Map<EntityType, Integer> playerInventory : allPlayerInventory) {
            batch.begin();
            font.setColor(Color.WHITE);
            if (!playerInventory.isEmpty()) {
                for (Map.Entry<EntityType, Integer> entry : playerInventory.entrySet()) {
                    EntityType entityType = entry.getKey();
                    int count = entry.getValue();
                    // Draw text for each type of vegetable collected by the player
                    font.draw(batch, entityType + ": " + count, posX, posY);
                    // Move to the next position for drawing
                    posX += 100; // Adjust as needed
                }
            }
            else {
                System.out.println("Debug: playerInventory is empty");
            }
            batch.end();
            // Move to the next row for drawing the inventory of the next player
            posY -= 20; // Adjust as needed
            posX = SCREEN_WIDTH * 0.01f + 250; // Reset X position
        }
    }


    public void renderL2(SpriteBatch batch) {
        List<Integer> allPlayerPoints = gameplayerManager.getAllPlayerPoints();

        for (int playerPoints: allPlayerPoints) {
            // Draw text showing total number of collectibles collected
            String collectiblesText = "Points: " + playerPoints;
            batch.begin();
            font.setColor(Color.WHITE);
            font.draw(batch, collectiblesText, SCREEN_WIDTH * 0.01f + 120, SCREEN_HEIGHT * 0.9f - 10);
            batch.end();
        }
    }
}

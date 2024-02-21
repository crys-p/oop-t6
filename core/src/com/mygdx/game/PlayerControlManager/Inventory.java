package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final PlayerControlManager playerControlManager;
    private final BitmapFont font;
    private final Map<Item, Integer> items;

    public Inventory(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        items = new HashMap<>();
        this.font = new BitmapFont();

    }

    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void removeItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity <= quantity) {
                items.remove(item);
            } else {
                items.put(item, currentQuantity - quantity);
            }
        }
    }

    public int getTotalItems() {
        int total = 0;
        for (int quantity : items.values()) {
            total += quantity;
        }
        return total;
    }

    public void render(SpriteBatch batch) {
        // int playerEntityID = playerControlManager.getPlayerEntityID(); // Get the player entity ID from PlayerControlManager

        // Get the total number of collectibles collected by the player
        // int totalCollectibles = playerControlManager.getPlayer(playerEntityID).getTotalItems(); // CRYSTAL COME BACK

        // Define the position of the health bar
        float posX = Gdx.graphics.getWidth() * 0.4f; // Distance from the left edge of the screen
        float posY = Gdx.graphics.getHeight() * 0.05f; // Distance from the top edge of the screen

        batch.begin();
        // Draw text showing total number of collectibles collected // CRYSTAL COME BACK
//        String collectiblesText = "Stars: " + totalCollectibles;
//        font.setColor(Color.WHITE);
//        font.draw(batch, collectiblesText, posX, posY);
        batch.end();
    }

    public boolean contains(Item item) {
        return items.containsKey(item);
    }
}

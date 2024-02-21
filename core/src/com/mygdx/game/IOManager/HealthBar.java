package com.mygdx.game.IOManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;

import java.util.HashMap;
import java.util.List;

public class HealthBar {
    protected static final float BAR_WIDTH = 500;
    protected static final float BAR_HEIGHT = 15;
    protected final BitmapFont font;
    protected final PlayerControlManager playerControlManager;

    public HealthBar(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.font = new BitmapFont();
    }

    public void render(ShapeRenderer shape, SpriteBatch batch) {
//        int playerEntityID = playerControlManager.getPlayerEntityID(); // Get the player entity ID from PlayerControlManager
//        Player player = playerControlManager.getPlayer(playerEntityID); // Get the player using the entity ID
//        if (player == null) {
//            System.out.println("Player not found");
//            return; // Player not found with the provided entity ID
//        }

        // Define the position of the health bar
        float healthBarX = Gdx.graphics.getWidth() * 0.01f; // Distance from the left edge of the screen
        float healthBarY = Gdx.graphics.getHeight() * 0.6f; // Distance from the top edge of the screen

        // Health Stats are defined as: currentHealth, maxHealth
        HashMap<Integer, List<Integer>> allPlayerHealthStats = playerControlManager.getAllPlayerHealthStats();
        int numPlayers = allPlayerHealthStats.size();

        // Draw Health bar for the number of players
        for (int i = 0; i < numPlayers; i++) {
            if (i != 0) {
                healthBarY -= BAR_HEIGHT + 50;
            }
            // Calculate Health percentage
            int currentHealth = allPlayerHealthStats.get(i).get(0);
            int maxHealth = allPlayerHealthStats.get(i).get(1);

            shape.begin(ShapeRenderer.ShapeType.Filled);
            // Draw the health bar background
            shape.setColor(Color.RED);
            shape.rect(healthBarX, healthBarY, BAR_WIDTH , BAR_HEIGHT);

            // Draw the remaining health bar
            shape.setColor(Color.GREEN);
            shape.rect(healthBarX, healthBarY, (BAR_WIDTH) * currentHealth / maxHealth, BAR_HEIGHT);
            shape.end();

            // Begin batch for drawing text
            batch.begin();

            // Draw text showing current health
            String healthText = "Health: " + currentHealth + "/" + maxHealth;
            font.setColor(Color.WHITE);
            font.draw(batch, healthText, healthBarX, healthBarY - 10);

            // End batch
            batch.end();
        }
    }


    public void dispose() {
        font.dispose();
    }
}

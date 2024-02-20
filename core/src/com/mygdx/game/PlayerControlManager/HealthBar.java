package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBar {
    private static final float BAR_WIDTH = 100;
    private static final float BAR_HEIGHT = 10;

    private final PlayerControlManager playerControlManager;

    public HealthBar(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
    }

    public void render(ShapeRenderer shapeRenderer, float playerX, float playerY, float playerWidth, float playerHeight) {
        int health = playerControlManager.getHealth();
        float healthPercentage = (float) health / playerControlManager.getMaxHealth();
        Character player = playerControlManager.getCharacters().get(0);
//        float playerHeight = player.getHeight();
//        float playerWidth = player.getWidth();

        // Set the color for the health bar
        shapeRenderer.setColor(Color.RED);

        // Draw the background of the health bar
        shapeRenderer.rect(playerX + playerWidth / 2, playerY + playerHeight, BAR_WIDTH, BAR_HEIGHT);

        // Set the color for the remaining health
        shapeRenderer.setColor(Color.GREEN);

        // Calculate the position of the health bar relative to the player's head
        float barX = playerX + playerWidth / 2;
        float barY = playerY + playerHeight;

        // Draw the remaining health bar based on the health percentage
        shapeRenderer.rect(barX, barY, BAR_WIDTH * healthPercentage, BAR_HEIGHT);
    }

}

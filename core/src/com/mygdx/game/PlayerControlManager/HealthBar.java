package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class HealthBar {
    protected static final float BAR_WIDTH = 500;
    protected static final float BAR_HEIGHT = 25;
    private static final int BAR_OFFSET_X = 10;
    private static final int BAR_OFFSET_Y = 10;
    private final BitmapFont font;
    private final PlayerControlManager playerControlManager;

    public HealthBar(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.font = new BitmapFont();
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch) {
        Player player = playerControlManager.getPlayer(0); // Get the player from PlayerControlManager
        if (player == null) {
            System.out.println("No player");
            return; // Player not found, cannot render health bar
        }

        int health = player.getHealth();
        float healthPercentage = (float) health / player.getMaxHealth();

        // Draw the health bar background
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(BAR_OFFSET_X, Gdx.graphics.getHeight() - BAR_OFFSET_Y - BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);

        // Draw the remaining health bar
        shapeRenderer.setColor(Color.GREEN);
        float barY = Gdx.graphics.getHeight() - BAR_OFFSET_Y - BAR_HEIGHT;
        shapeRenderer.rect(BAR_OFFSET_X, barY, BAR_WIDTH * healthPercentage, BAR_HEIGHT);

        // Begin batch for drawing text
        batch.begin();

        // Draw text showing current health
        String healthText = "Health: " + health + "/" + player.getMaxHealth();
        font.setColor(Color.WHITE);
        font.draw(batch, healthText, BAR_OFFSET_X + 10, Gdx.graphics.getHeight() - BAR_OFFSET_Y - 30);

        // End batch
        batch.end();
    }

    public void dispose() {
        font.dispose();
    }
}

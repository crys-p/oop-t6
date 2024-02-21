package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.EntityManager.EntityManager;

public class HealthBar {
    protected static final float BAR_WIDTH = 500;
    protected static final float BAR_HEIGHT = 25;
    protected final BitmapFont font;
    protected final PlayerControlManager playerControlManager;

    public HealthBar(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.font = new BitmapFont();
    }

    public void render(ShapeRenderer shapeRenderer, SpriteBatch batch) {
            int playerEntityID = playerControlManager.getPlayerEntityID(); // Get the player entity ID from PlayerControlManager
        Player player = playerControlManager.getPlayer(playerEntityID); // Get the player using the entity ID
        if (player == null) {
            System.out.println("Player not found");
            return; // Player not found with the provided entity ID
        }

        int health = player.getHealth();
        float healthPercentage = (float) health / player.getMaxHealth();

        // Define the position of the health bar
        float healthBarX = Gdx.graphics.getWidth() * 0.01f; // Distance from the left edge of the screen
        float healthBarY = Gdx.graphics.getHeight() * 0.6f; // Distance from the top edge of the screen

        // Debugging information
//        System.out.println("Health Bar X: " + healthBarX);
//        System.out.println("Health Bar Y: " + healthBarY);

        // Draw the health bar background
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(healthBarX, healthBarY, BAR_WIDTH , BAR_HEIGHT );

        // Draw the remaining health bar
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(healthBarX, healthBarY, (BAR_WIDTH) * healthPercentage, BAR_HEIGHT);

        // Begin batch for drawing text
        batch.begin();

        // Draw text showing current health
        String healthText = "Health: " + health + "/" + player.getMaxHealth();
        font.setColor(Color.WHITE);
        font.draw(batch, healthText, healthBarX, healthBarY - 10);

        // End batch
        batch.end();
    }


    public void dispose() {
        font.dispose();
    }
}

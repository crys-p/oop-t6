package com.mygdx.engine.IOManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.player.GamePlayerManager;

import java.util.List;

import static com.mygdx.engine.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.engine.IOManager.IOManager.SCREEN_WIDTH;

public class InventoryDisplay {

    private GamePlayerManager gameplayerManager;
    protected final BitmapFont font;

    protected InventoryDisplay(GamePlayerManager gameplayerManager) {
        this.gameplayerManager = gameplayerManager;
        this.font = new BitmapFont();
    }
    protected void render(SpriteBatch batch) {
        List<Integer> allPlayerInventory = gameplayerManager.getAllPlayerInventory();
        List<Integer> allPlayerPoints = gameplayerManager.getAllPlayerPoints();

        // Define the position of the inventory
        float posX = SCREEN_WIDTH * 0.01f + 130; // Distance from the left edge of the screen
        float posY = SCREEN_HEIGHT * 0.9f - 10; // Distance from the top edge of the screen

        for (int numPlayerCollectibles: allPlayerInventory) {
            // Draw text showing total number of collectibles collected
            String collectiblesText = "Stars: " + numPlayerCollectibles;
            batch.begin();
                font.setColor(Color.WHITE);
                font.draw(batch, collectiblesText, posX, posY);
            batch.end();
            posY -= 65;
        }

        posX = SCREEN_WIDTH * 0.01f + 250; // Distance from the left edge of the screen
        posY = SCREEN_HEIGHT * 0.9f - 10; // Distance from the top edge of the screen

        for (int playerPoints: allPlayerPoints) {
            // Draw text showing total number of collectibles collected
            String collectiblesText = "Points: " + playerPoints;
            batch.begin();
            font.setColor(Color.WHITE);
            font.draw(batch, collectiblesText, posX, posY);
            batch.end();
            posY -= 65;
        }
    }
}

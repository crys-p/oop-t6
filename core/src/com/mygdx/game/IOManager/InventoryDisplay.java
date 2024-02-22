package com.mygdx.game.IOManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.PlayerControlManager.PlayerControlManager;

import java.util.List;

import static com.mygdx.game.IOManager.IOManager.SCREEN_HEIGHT;
import static com.mygdx.game.IOManager.IOManager.SCREEN_WIDTH;

public class InventoryDisplay {

    private PlayerControlManager playerControlManager;
    protected final BitmapFont font;

    protected InventoryDisplay(PlayerControlManager playerControlManager) {
        this.playerControlManager = playerControlManager;
        this.font = new BitmapFont();
    }
    protected void render(SpriteBatch batch) {
        List<Integer> allPlayerInventory = playerControlManager.getAllPlayerInventory();

        // Define the position of the inventory
        float posX = SCREEN_WIDTH * 0.01f + 130; // Distance from the left edge of the screen
        float posY = SCREEN_HEIGHT * 0.9f - 10; // Distance from the top edge of the screen

        for (int numPlayerCollectibles: allPlayerInventory) {
            // Draw text showing total number of collectibles collected // CRYSTAL COME BACK
            String collectiblesText = "Stars: " + numPlayerCollectibles;
            font.setColor(Color.WHITE);
            font.draw(batch, collectiblesText, posX, posY);
            posY -= 65;
        }
    }
}

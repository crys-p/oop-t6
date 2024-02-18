package com.mygdx.game.SceneManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.mygdx.game.GameMaster;
import com.badlogic.gdx.graphics.Color;

public class GameScene extends Scene {

    public GameScene(Game game) {
        super(game);
        setBackgroundColor(Color.BLACK); // setting of background color for end scene
    }

    @Override
    public void show() {
        // Logic when the game scene is shown
    }

    @Override
    public void hide() {
        // Logic when the game scene is hidden
    }

    @Override
    public void render(float delta) {
        clearScreen();
        // Rendering logic for the game scene
    }
}
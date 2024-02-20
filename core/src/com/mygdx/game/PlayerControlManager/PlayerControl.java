package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector;

public class PlayerControl {
    private Character playerCharacter;
    private Vector playerPosition;
    private Sprite sprite;
    private String username;

    public Sprite getSprite() { return sprite;}
    public Vector getPlayerPosition() { return playerPosition;}
    public void setSprite(Sprite sprite) { this.sprite = sprite; }
    public void setPlayerPosition(Vector position) {this.playerPosition = position; }

}

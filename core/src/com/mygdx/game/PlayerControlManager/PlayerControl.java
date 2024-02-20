package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector;
import com.mygdx.game.EntityManager.Character;

public class PlayerControl {
    protected Character playerCharacter;
    protected Vector playerPosition;
    protected Sprite sprite;
    private String username;

    protected Sprite getSprite() { return sprite;}
    protected Vector getPlayerPosition() { return playerPosition;}
    protected void setSprite(Sprite sprite) { this.sprite = sprite; }
    protected void setPlayerPosition(Vector position) {this.playerPosition = position; }

}

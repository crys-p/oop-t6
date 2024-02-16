package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;

public abstract class Enemy extends Entity {
    protected Color color;
    protected int damage;

    Enemy(float x, float y, float velocityX, float velocityY, Color color) {
        super(x, y, velocityX, velocityY);
        this.color = color;
    }

    public abstract void inflictDamage(Player player);

}

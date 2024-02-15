package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;

public abstract class Enemy extends Entity {
    protected Color color;

    Enemy(float x, float y, float speed, Color color) {
        super(x, y, speed);
        this.color = color;
    }

    public abstract void inflictDamage(Player player);

}

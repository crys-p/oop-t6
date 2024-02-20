package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;

abstract class Enemy extends Entity {
    protected Color color;

    // TODO: Set damage in respective classes
    protected int damage;

    Enemy(float x, float y, float velocityX, float velocityY, Color color) {
        super(x, y, velocityX, velocityY);
        this.color = color;
    }
}

package com.mygdx.game.EntityManager;


abstract class Enemy extends Entity implements AIControlled {
    protected float damage;

    Enemy(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY, image);
    }

    protected void setDamage(float dmg) {
        this.damage = dmg;
    }

    protected float getDamage() {
        return this.damage;
    }
}

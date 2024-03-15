package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;
import com.mygdx.engine.Movement.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;


public class Character extends Entity implements iCollidable {

    private PlayerMovement behaviour;

    public Character(float x, float y, float speed, Texture texture) {
        super(x, y, speed, texture);
    }

    public Character(float x, float y, float speed, Texture texture, PlayerMovement behaviour) {
        super(x, y, speed, texture);
        this.behaviour = behaviour;
    }

    protected void movement() {
        float[] newPosition = behaviour.calculateMovement(this.getX(), this.getY(), this.getSpeed() * Gdx.graphics.getDeltaTime());
        this.setX(newPosition[0]);
        this.setY(newPosition[1]);
        behaviour.setPlayerInstructions(null);
        updateBoundingBox();
    }

    protected void setPlayerInstructions(PlayerInstructions instructions) {
        this.behaviour.setPlayerInstructions(instructions);
    }

    protected void logConsole() {
        System.out.printf("I am a character at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

}

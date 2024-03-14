package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.iCollidable;
import com.mygdx.engine.Movement.Behaviour;
import com.mygdx.engine.Movement.PlayerBehaviour;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.game.Player1Behaviour;


public class Character extends Entity implements iCollidable {
    private float speed;

    private PlayerInstructions playerInstructions;

    private Behaviour behaviour;

    public Character(float x, float y, float speed, String image) {
        super(x, y, image);
        this.speed = speed;
    }

    public Character(float x, float y, float speed, String image, Player1Behaviour behaviour) {
        super(x, y, image);
        this.speed = speed;
        this.behaviour = behaviour;
    }

    protected void movement() {
        float[] newPosition = behaviour.calculateMovement(playerInstructions, this.getX(), this.getY(), this.getSpeed() * Gdx.graphics.getDeltaTime());
        this.setX(newPosition[0]);
        this.setY(newPosition[1]);
        updateBoundingBox();
        playerInstructions = null;
    }

    protected void setPlayerInstructions(PlayerInstructions instructions) {
        this.playerInstructions = instructions;
    }

    protected void logConsole() {
        System.out.printf("I am a character at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }


    protected float getSpeed() {
        return this.speed;
    }

    protected void incrementSpeed(float value) {
        this.speed += value;
    }

    protected void setSpeed(float value) {
        this.speed = value;
    }

    public PlayerInstructions getPlayerInstruction() {
        return playerInstructions;
    }
}
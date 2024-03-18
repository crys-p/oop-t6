package com.mygdx.engine.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;


public abstract class PlayableCharacter extends Entity implements iCollidable {

    protected PlayerMovement movement;

    public PlayableCharacter(float x, float y, float speed, Texture texture) {
        super(x, y, speed, texture);
    }

    public PlayableCharacter(float x, float y, float speed, Texture texture, PlayerMovement behaviour) {
        super(x, y, speed, texture);
        this.movement = behaviour;
    }

    protected void movement() {
        float[] newPosition = movement.calculateMovement(this.getX(), this.getY(), this.getSpeed() * Gdx.graphics.getDeltaTime());
        this.setX(newPosition[0]);
        this.setY(newPosition[1]);
        movement.setPlayerInstructions(null);
        updateBoundingBox();
    }

    protected PlayerInstructions getPlayerInstructions() {
        return this.movement.getPlayerInstructions();
    }

    protected void setPlayerInstructions(PlayerInstructions instructions) {
        this.movement.setPlayerInstructions(instructions);
    }

    protected void logConsole() {
        System.out.printf("I am a character at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

    @Override
    public Rectangle getBoundingBox() {
        return this.boundingBox;
    }
}

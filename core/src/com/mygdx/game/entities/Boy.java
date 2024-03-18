package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;


public class Boy extends PlayableCharacter {

    private Texture[] textures;

    public Boy(float x, float y, float speed, Texture texture, Texture[] textures, PlayerMovement behaviour) {
        super(x, y, speed, texture);
        this.movement = behaviour;
        this.textures = textures;
    }

    protected void movement() {
        float[] newPosition = movement.calculateMovement(this.getX(), this.getY(), this.getSpeed() * Gdx.graphics.getDeltaTime());
        updateSpriteDirection();
        this.setX(newPosition[0]);
        this.setY(newPosition[1]);
        updateBoundingBox();
        movement.setPlayerInstructions(null);
    }

    // Update sprite texture when moving
    private void updateSpriteDirection() {
        PlayerInstructions playerInstructions = this.getPlayerInstructions();
        if (playerInstructions != null) {
            switch (playerInstructions) {
                case UP:
                    this.setTexture(textures[0]);
                    break;
                case DOWN:
                    this.setTexture(textures[1]);
                    break;
                case LEFT:
                    this.setTexture(textures[2]);
                    break;
                case RIGHT:
                    this.setTexture(textures[3]);
                    break;
            }
        }
    }

    protected void logConsole() {
        System.out.printf("I am a boy at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

}

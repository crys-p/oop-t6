package com.mygdx.game.GameEntities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

import java.util.Vector;

public class GameCharacter extends PlayableCharacter {
    private Texture[] textures;

    private float[] previousPosition;
    public GameCharacter(float x, float y, float speed, Texture texture) {
        super(x, y, speed, texture);
        this.setMoving(true);
        previousPosition = new float[] {x, y};
    }

    public GameCharacter(float x, float y, float speed, Texture texture, Texture[] textures, PlayerMovement behaviour) {
        super(x, y, speed, texture, behaviour);
        this.textures = textures;
        this.setMoving(true);
        previousPosition = new float[] {x, y};
    }

    protected void movement() {
        this.previousPosition[0] = this.getX();
        this.previousPosition[1] = this.getY();
        this.updateSpriteDirection();
        super.movement();
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

    public void moveBackwards() {
        this.setX(this.previousPosition[0]);
        this.setY(this.previousPosition[1]);
    }
}

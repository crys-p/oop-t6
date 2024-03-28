package com.mygdx.game.GameEntities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.MovementStrategy.PlayerMovement;
import com.mygdx.engine.PlayerManager.PlayerInstructions;


public class Boy extends GameCharacter {

    public Boy(float x, float y, float speed, Texture texture, Texture[] textures, PlayerMovement playerMovement) {
        super(x, y, speed, texture, textures, playerMovement);
    }


    protected void logConsole() {
        System.out.printf("I am a boy at %f, %f, my EntityID is %d\n", this.getX(), this.getY(), this.getEntityID());
    }

}

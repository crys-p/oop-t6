package com.mygdx.engine.Movement;

import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class PlayerBehaviour extends Behaviour {
    protected PlayerInstructions playerInstructions;

    public void setPlayerInstructions(PlayerInstructions instructions) {
        this.playerInstructions = instructions;
    }
}

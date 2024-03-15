package com.mygdx.engine.Movement;

import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class PlayerMovement extends Movement {
    protected PlayerInstructions playerInstructions;

    public void setPlayerInstructions(PlayerInstructions instructions) {
        this.playerInstructions = instructions;
    }
}

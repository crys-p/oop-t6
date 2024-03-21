package com.mygdx.engine.MovementStrategy;

import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class PlayerMovement implements Movement {
    protected PlayerInstructions playerInstructions;

    public void setPlayerInstructions(PlayerInstructions instructions) {
        this.playerInstructions = instructions;
    }

    public PlayerInstructions getPlayerInstructions() {
        return this.playerInstructions;
    }
}

package com.mygdx.engine.Movement;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class Behaviour {
    // for AI
    public abstract float[] calculateMovement(float x, float y, float v);

    // for player
    public abstract float[] calculateMovement(PlayerInstructions instructions, float x, float y, float speed);
}

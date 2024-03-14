package com.mygdx.engine.Movement;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class PlayerBehaviour extends Behaviour {
    public abstract float[] calculateMovement(float x, float y, float speed);
}

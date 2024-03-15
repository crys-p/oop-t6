package com.mygdx.engine.Movement;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public abstract class PlayerBehaviour extends Behaviour {
    @Override
    public float[] calculateMovement(float x, float y, float speed) {
        // this method is for AI movement so it does nothing in player movement
        return new float[] {x,y};
    }

}

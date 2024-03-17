package com.mygdx.engine.AIControlManager;

import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.Movement.Movement;

public abstract class AIMovement extends Movement {
    protected Entity entity;

    public AIMovement(Entity entity) {
        this.entity = entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void update() {
    }
}

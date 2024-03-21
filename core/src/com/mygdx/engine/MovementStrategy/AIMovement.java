package com.mygdx.engine.MovementStrategy;

import com.mygdx.engine.EntityManager.Entity;

public abstract class AIMovement implements Movement {
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

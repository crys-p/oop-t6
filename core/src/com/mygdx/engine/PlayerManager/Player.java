package com.mygdx.engine.PlayerManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.GameEntities.EntityType;
import com.mygdx.game.player.PlayerControlConfigs;
import java.util.HashMap;
import java.util.Map;

public abstract class Player {
    protected int playerControlledEntityID = -1;
    protected final int maxHealth = 100;
    protected int health;
    protected PlayerController playerController;
    protected Map<EntityType, Integer> inventory;;

    protected Player() {
        this.health = maxHealth;
        this.inventory = new HashMap<>();
        this.playerController = new PlayerController();
    }

    protected abstract int getHealth();

    protected abstract void setHealth(int health);

    protected abstract int getMaxHealth();

    protected abstract void setPlayerKeyConfigs(PlayerControlConfigs control);

    protected abstract PlayerControlConfigs getPlayerKeyConfigs();

    protected abstract void move(Integer key, EntityManager entityManager);
}

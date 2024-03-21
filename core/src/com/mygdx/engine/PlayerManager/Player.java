package com.mygdx.engine.PlayerManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.game.PlayerControlConfigs;
import com.mygdx.game.player.Inventory;

public abstract class Player {
    protected int playerControlledEntityID = -1;
    protected final int maxHealth = 100;
    protected int health;
    protected PlayerController playerController;
    protected Inventory inventory;

    protected Player() {
        this.health = maxHealth;
        this.inventory = new Inventory();
        this.playerController = new PlayerController();
    }

    protected abstract void setPlayerControlledEntityID(int entityID);

    protected abstract void addToInventory(Item item, int quantity);

    protected abstract int getHealth();

    protected abstract void setHealth(int health);

    protected abstract int getMaxHealth();

    protected abstract void setPlayerKeyConfigs(PlayerControlConfigs control);

    protected abstract PlayerControlConfigs getPlayerKeyConfigs();

    protected abstract int getPlayerControlledEntityID();

    protected abstract int getInventoryCount();

    protected abstract void clearInventory();

    protected abstract void move(Integer key, EntityManager entityManager);
}

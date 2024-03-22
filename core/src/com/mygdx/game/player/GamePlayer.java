package com.mygdx.game.player;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.PlayerManager.Item;
import com.mygdx.engine.PlayerManager.Player;
import com.mygdx.engine.PlayerManager.PlayerController;
import com.mygdx.engine.PlayerManager.PlayerInstructions;

public class GamePlayer extends Player {
    private int playerControlledEntityID = -1;
    private final int maxHealth = 100;
    private int health;
    private PlayerController playerController;
    protected Inventory inventory;
    private int points = 0;
    public GamePlayer() {
        this.health = maxHealth;
        this.inventory = new Inventory();
        this.playerController = new PlayerController();
    }

    public void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
    }

    public void addToInventory(Item item, int quantity) {
        inventory.addItem(item, quantity);
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    public void setPlayerKeyConfigs(PlayerControlConfigs control) {
        this.playerController.setKeyConfigs(control);
    }

    @Override
    public PlayerControlConfigs getPlayerKeyConfigs() {
        return this.playerController.getKeyConfigs();
    }

    public int getPlayerControlledEntityID() {
        return this.playerControlledEntityID;
    }

    public int getInventoryCount() {
        return inventory.getTotalItems();
    }

    public void clearInventory() {
        inventory.clear();
    }

    @Override
    public void move(Integer key, EntityManager entityManager) {
        PlayerInstructions instr = this.playerController.getPlayerMovement(key);
        if (instr != null) {
            entityManager.inputMovement(this.playerControlledEntityID, instr);
        }
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementPoints(int addPoints) {
        System.out.println("adding " + addPoints);
        this.points += addPoints;
    }

}

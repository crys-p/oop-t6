package com.mygdx.game.player;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.EntityManager.PlayableCharacter;
import com.mygdx.engine.PlayerManager.Item;
import com.mygdx.engine.PlayerManager.Player;
import com.mygdx.engine.PlayerManager.PlayerController;
import com.mygdx.engine.PlayerManager.PlayerInstructions;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.GameEntities.EntityType;

import java.util.HashMap;
import java.util.Map;

public class GamePlayer extends Player {
    private int playerControlledEntityID = -1;
    private final int maxHealth = 100;
    private int health;
    private PlayerController playerController;
    protected final Map<EntityType, Integer> inventory;
    private int points = 0;
    public GamePlayer() {
        this.health = maxHealth;
        inventory = new HashMap<>();
        this.playerController = new PlayerController();
    }

    protected void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
    }

    @Override
    protected int getHealth() {
        return this.health;
    }

    @Override
    protected void setHealth(int health) {
        this.health = health;
    }

    @Override
    protected int getMaxHealth() {
        return this.maxHealth;
    }

    @Override
    protected void setPlayerKeyConfigs(PlayerControlConfigs control) {
        this.playerController.setKeyConfigs(control);
    }

    @Override
    public PlayerControlConfigs getPlayerKeyConfigs() {
        return this.playerController.getKeyConfigs();
    }

    protected int getPlayerControlledEntityID() {
        return this.playerControlledEntityID;
    }

    public Map<EntityType, Integer> getInventory() {
        return (Map<EntityType, Integer>) inventory;
    }

    protected void addItemToInventory(EntityType item) {
        if (inventory.containsKey(item)) {
            inventory.put(item, inventory.get(item) + 1);
        } else {
            inventory.put(item, 1);
        }
    }

    protected int getInventoryCount() {
        int totalInventoryCount = 0;
        for (EntityType type : inventory.keySet()) {
            totalInventoryCount += inventory.get(type);
        }
        return totalInventoryCount;
    }

    protected void clearInventory() {
        inventory.clear();
    }

    @Override
    protected void move(Integer key, EntityManager entityManager) {
        PlayerInstructions instr = this.playerController.getPlayerMovement(key);
        if (instr != null) {
            try {
                PlayableCharacter player = (PlayableCharacter) entityManager.getEntity(this.playerControlledEntityID);
                player.setPlayerInstructions(instr);
            } catch (Exception e) {
                SimulationManager.getInstance().logError("Player move error: " + e);
            }
        }
    }

    protected int getPoints() {
        return this.points;
    }

    protected void setPoints(int points) {
        this.points = points;
    }

    protected void incrementPoints(int addPoints) {
        this.points += addPoints;
    }

    protected void recoverHealth(int addedHealth) {
        // add health but not beyond 100
        this.health = Math.min(this.health + addedHealth, maxHealth);
    }
}

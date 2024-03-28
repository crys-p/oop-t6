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

    public void setPlayerControlledEntityID(int entityID) {
        this.playerControlledEntityID = entityID;
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

    public Map<EntityType, Integer> getInventory() {
        return (Map<EntityType, Integer>) inventory;
    }

    public void clearInventory() {
        inventory.clear();
    }

    @Override
    public void move(Integer key, EntityManager entityManager) {
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

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void incrementPoints(int addPoints) {
        this.points += addPoints;
    }

}

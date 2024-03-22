// PlayerManager.java
package com.mygdx.engine.PlayerManager;

import com.mygdx.engine.EntityManager.EntityManager;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.player.PlayerControlConfigs;

import java.util.*;

public abstract class PlayerManager {
    protected final ArrayList<Player> allPlayers;
    protected final Map<Player, Integer> playerEntityMap; // Map to store GamePlayer instances and corresponding entity being controlled

    protected final EntityManager entityManager;
    protected SimulationManager simulationManager;

    public PlayerManager(EntityManager entityManager) {
        this.entityManager = entityManager;
        playerEntityMap = new HashMap<>();
        allPlayers = new ArrayList<>();

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("PlayerManager initialized"); // Log initialization message
    }

    public abstract void createPlayers(int qty);

    public abstract void removePlayer(int index);

    public abstract void handlePressedKeys(List<Integer> keys);

    public abstract void setPlayerControl(int playerNumber, PlayerControlConfigs playerControl);

    public abstract PlayerControlConfigs getPlayerControls(int playerNumber);

    public abstract float[] getPlayerPosition(int playerNumber);
}

package com.mygdx.engine.PlayerManager;

import com.badlogic.gdx.Input;
import com.mygdx.game.PlayerControlConfigs;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class PlayerController {
    private final HashMap<PlayerControlConfigs, List<Integer>> keyMaps = new HashMap<>(); // stores keys used for each config
    private PlayerControlConfigs keyConfigs = PlayerControlConfigs.UDLR; // default key controls if not set

    PlayerController() {
        this.setKeyMaps();
    }
    private void setKeyMaps() {
        // set up keys available for each config
        keyMaps.put(PlayerControlConfigs.UDLR, Arrays.asList(Input.Keys.LEFT, Input.Keys.RIGHT, Input.Keys.UP, Input.Keys.DOWN));
        keyMaps.put(PlayerControlConfigs.WASD, Arrays.asList(Input.Keys.A, Input.Keys.D, Input.Keys.W, Input.Keys.S));
    }

    protected PlayerInstructions getPlayerMovement(Integer key) {
        // Check if this key is used for this player and returns predefined player instruction
        if (keyMaps.get(keyConfigs).contains(key)) {
            return getMovementFromKey(key);
        }
        return null;
    }

    // Get instruction based on key
    private PlayerInstructions getMovementFromKey(int key) {
        if (key == Input.Keys.LEFT || key == Input.Keys.A) {
            return PlayerInstructions.LEFT;
        } else if (key == Input.Keys.RIGHT || key == Input.Keys.D) {
            return PlayerInstructions.RIGHT;
        } else if (key == Input.Keys.UP || key == Input.Keys.W) {
            return PlayerInstructions.UP;
        } else if (key == Input.Keys.DOWN || key == Input.Keys.S) {
            return PlayerInstructions.DOWN;
        } else if (key == Input.Keys.SPACE) {
            return PlayerInstructions.JUMP;
        }
        return null; // No direction found for the given key in the control configuration
    }

    protected PlayerControlConfigs getKeyConfigs() {
        return this.keyConfigs;
    }

    protected void setKeyConfigs(PlayerControlConfigs control) {
        this.keyConfigs = control;
    }

}

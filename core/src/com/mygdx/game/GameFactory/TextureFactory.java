package com.mygdx.game.GameFactory;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.EntityType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class TextureFactory {
    HashMap<EntityType, String> entityImageMap;
    ArrayList<Texture> textureList;

    private SimulationManager simulationManager;

    public TextureFactory() {
        entityImageMap = new HashMap<>();
        textureList = new ArrayList<>();

        simulationManager = SimulationManager.getInstance(); // Obtain the instance of SimulationManager
        simulationManager.logInfo("Texture Manager initialized"); // Log initialization message
    }

    public void create() {
        entityImageMap.put(EntityType.CHARACTER, "player.png");
        entityImageMap.put(EntityType.ENEMY, "fire.png");
        entityImageMap.put(EntityType.COLLECTIBLE, "star.png");
    }

    public Texture getTexture(EntityType entityType) {
        String image = entityImageMap.get(entityType);
        return new Texture(Gdx.files.internal(image));
    }

}

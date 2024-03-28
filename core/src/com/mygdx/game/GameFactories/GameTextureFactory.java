package com.mygdx.game.GameFactories;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.engine.Factory.AbstractTextureFactory;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.GameEntities.EntityType;

public class GameTextureFactory extends AbstractTextureFactory {
    public GameTextureFactory() {
        SimulationManager.getInstance().logInfo("Texture Manager initialized"); // Log initialization message
    }

    protected void createSingleTextureEntity() {
        // Non-Playable
        // Vegetables
        entityImageMap.put(EntityType.BROCCOLI.getId(), "sprites/vegetable/broccoli.png");
        entityImageMap.put(EntityType.CABBAGE.getId(), "sprites/vegetable/cabbage.png");
        entityImageMap.put(EntityType.CARROT.getId(), "sprites/vegetable/carrot.png");
        entityImageMap.put(EntityType.BOKCHOY.getId(), "sprites/vegetable/bok_choy.png");

        // Fruits
        entityImageMap.put(EntityType.APPLE.getId(), "sprites/fruit/apple.png");
        entityImageMap.put(EntityType.BANANA.getId(), "sprites/fruit/banana.png");
        entityImageMap.put(EntityType.WATERMELON.getId(), "sprites/fruit/watermelon_slice.png");

        // Dessert
        entityImageMap.put(EntityType.COOKIE.getId(), "sprites/dessert/cookie_chocolate_chip.png");
        entityImageMap.put(EntityType.CUPCAKE.getId(), "sprites/dessert/cupcake.png");
        entityImageMap.put(EntityType.DOUGHNUT.getId(), "sprites/dessert/doughnut.png");
        entityImageMap.put(EntityType.ICECREAM.getId(), "sprites/dessert/ice_cream_bar_02.png");
        entityImageMap.put(EntityType.SUNDAE.getId(), "sprites/dessert/ice_cream_sundae_01.png");

        // Fast Food
        entityImageMap.put(EntityType.DRUMSTICK.getId(), "sprites/fastfood/chicken_drumstick_02.png");
        entityImageMap.put(EntityType.FRIES.getId(), "sprites/fastfood/french_fries.png");
        entityImageMap.put(EntityType.BURGER.getId(), "sprites/fastfood/sandwich_burger.png");
        entityImageMap.put(EntityType.SODA.getId(), "sprites/fastfood/soda_glass.png");

        // Wall
        entityImageMap.put(EntityType.hBRICKWALL.getId(), "sprites/building-block-assets/Brickwall.png");
        entityImageMap.put(EntityType.vBRICKWALL.getId(), "sprites/building-block-assets/Brickwall_vertical.png");
    }

    protected void createMultiTextureEntity() {
        // Playable
        String[] sprites = {
                "sprites/character/elf_back_idle.png", // up
                "sprites/character/elf_front_idle.png", // down
                "sprites/character/elf_side02_idle.png", // left
                "sprites/character/elf_side01_idle.png" // right
        };
        entityMultiImageMap.put(EntityType.BOY.getId(), sprites);
    }

    public float getTextureWidth(int typeId) {
        Texture texture = new Texture(entityImageMap.get(typeId));
        float width = texture.getWidth();
        texture.dispose();
        return width;
    }

    public float getTextureHeight(int typeId) {
        Texture texture = new Texture(entityImageMap.get(typeId));
        float height = texture.getHeight();
        texture.dispose();
        return height;
    }
}

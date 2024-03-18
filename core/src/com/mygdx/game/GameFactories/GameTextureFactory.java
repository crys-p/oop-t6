package com.mygdx.game.GameFactories;

import com.mygdx.engine.Factory.AbstractTextureFactory;
import com.mygdx.engine.SimulationManager.SimulationManager;
import com.mygdx.game.entities.EntityType;

public class GameTextureFactory extends AbstractTextureFactory {
    public GameTextureFactory() {
        SimulationManager.getInstance().logInfo("Texture Manager initialized"); // Log initialization message
    }

    public void create() {
        createSingleTextureEntity();
        createMultiTextureEntity();
    }

    protected void createSingleTextureEntity() {
        // Non-Playable
        // Vegetables
        entityImageMap.put(EntityType.BROCCOLI, "sprites/vegetable/broccoli.png");
        entityImageMap.put(EntityType.CABBAGE, "sprites/vegetable/cabbage.png");
        entityImageMap.put(EntityType.CARROT, "sprites/vegetable/carrot.png");
        entityImageMap.put(EntityType.BOKCHOY, "sprites/vegetable/bok_choy.png");

        // Fruits
        entityImageMap.put(EntityType.APPLE, "sprites/fruit/apple.png");
        entityImageMap.put(EntityType.BANANA, "sprites/fruit/banana.png");
        entityImageMap.put(EntityType.WATERMELON, "sprites/fruit/watermelon_slice.png");

        // Dessert
        entityImageMap.put(EntityType.COOKIE, "sprites/dessert/cookie_chocolate_chip.png");
        entityImageMap.put(EntityType.CUPCAKE, "sprites/dessert/cupcake.png");
        entityImageMap.put(EntityType.DOUGHNUT, "sprites/dessert/doughnut.png");
        entityImageMap.put(EntityType.ICECREAM, "sprites/dessert/ice_cream_bar_02.png");
        entityImageMap.put(EntityType.SUNDAE, "sprites/dessert/ice_cream_sundae_01.png");

        // Fast Food
        entityImageMap.put(EntityType.DRUMSTICK, "sprites/fastfood/chicken_drumstick_02.png");
        entityImageMap.put(EntityType.FRIES, "sprites/fastfood/french_fries.png");
        entityImageMap.put(EntityType.BURGER, "sprites/fastfood/sandwich_burger.png");
        entityImageMap.put(EntityType.SODA, "sprites/fastfood/soda_glass.png");
    }

    protected void createMultiTextureEntity() {
        // Playable
        String[] sprites = {
                "sprites/character/elf_back_idle.png", // up
                "sprites/character/elf_front_idle.png", // down
                "sprites/character/elf_side02_idle.png", // left
                "sprites/character/elf_side01_idle.png" // right
        };
        entityMultiImageMap.put(EntityType.BOY, sprites);
    }

}

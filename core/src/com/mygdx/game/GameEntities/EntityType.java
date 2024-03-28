package com.mygdx.game.GameEntities;

import java.util.HashMap;

public enum EntityType {
    BOY(1), GIRL(2),
    BROCCOLI(3), CABBAGE(4), CARROT(5), BOKCHOY(6),
    APPLE(7), BANANA(8), WATERMELON(9),
    COOKIE(10), CUPCAKE(11), DOUGHNUT(12), ICECREAM(13), SUNDAE(14),
    DRUMSTICK(15), FRIES(16), BURGER(17), SODA(18),
    vBRICKWALL(19), hBRICKWALL(20),
    VEGGIECART(21);
    private final int id;
    private static final HashMap<Integer, EntityType> idToEnumMap = new HashMap<>();

    static {
        for (EntityType type : EntityType.values()) {
            idToEnumMap.put(type.getId(), type);
        }
    }

    EntityType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static EntityType getEntityType(int id) {
        return idToEnumMap.get(id);
    }
}

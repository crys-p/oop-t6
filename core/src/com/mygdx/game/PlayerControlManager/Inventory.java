package com.mygdx.game.PlayerControlManager;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Item, Integer> items;

    public Inventory() {
        items = new HashMap<>();
    }

    public void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    public void removeItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity <= quantity) {
                items.remove(item);
            } else {
                items.put(item, currentQuantity - quantity);
            }
        }
    }

    public int getTotalItems() {
        int total = 0;
        for (int quantity : items.values()) {
            total += quantity;
        }
        return total;
    }

    public boolean contains(Item item) {
        return items.containsKey(item);
    }
}

package com.mygdx.game.PlayerControlManager;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private final Map<Item, Integer> items;

    protected Inventory() {
        items = new HashMap<>();
    }

    protected void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

    // Remove particular item
    protected void removeItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            int currentQuantity = items.get(item);
            if (currentQuantity <= quantity) {
                items.remove(item);
            } else {
                items.put(item, currentQuantity - quantity);
            }
        }
    }

    // Get total amount of items
    protected int getTotalItems() {
        int total = 0;
        for (int quantity : items.values()) {
            total += quantity;
        }
        return total;
    }

    // Clear all items in inventory
    protected void clear() {
        items.clear();
    }

}

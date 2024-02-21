package com.mygdx.game.PlayerControlManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private final BitmapFont font;
    private final Map<Item, Integer> items;

    protected Inventory() {
        items = new HashMap<>();
        this.font = new BitmapFont();

    }

    protected void addItem(Item item, int quantity) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + quantity);
        } else {
            items.put(item, quantity);
        }
    }

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

    protected int getTotalItems() {
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

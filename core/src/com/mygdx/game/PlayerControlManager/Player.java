package com.mygdx.game.PlayerControlManager;

public class Player {
    private float positionX;
    private float positionY;
    private int totalItems; // Variable to keep track of total items

    // Constructor
    public Player(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.totalItems = 0; // Initialize totalItems to 0 when creating a new player
    }

    // Getters and setters for positionX and positionY
    public float getPositionX() {
        return positionX;
    }

    public void setPositionX(float positionX) {
        this.positionX = positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    public void setPositionY(float positionY) {
        this.positionY = positionY;
    }

    // Getter and setter for totalItems
    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    // Method to increment totalItems
    public void incrementTotalItems() {
        totalItems++;
    }
}

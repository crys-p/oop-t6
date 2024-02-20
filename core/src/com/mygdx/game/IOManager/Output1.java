package com.mygdx.game.IOManager;

public class Output1 {
    private int displayHealth;
    private float framePosition;

    public Output1(int displayHealth, float framePosition) {
        this.displayHealth = displayHealth;
        this.framePosition = framePosition;
    }

    public int getDisplayHealth() {
        return displayHealth;
    }

    public void setDisplayHealth(int displayHealth) {
        this.displayHealth = displayHealth;
    }

    public float getFramePosition() {
        return framePosition;
    }

    public void setFramePosition(float framePosition) {
        this.framePosition = framePosition;
    }

    public void updatePlayerHealth(int Health) {

    }

    public void updateFramePosition(float Position) {

    }

}
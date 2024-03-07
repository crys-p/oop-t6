package com.mygdx.game.IOManager;

public class Mouse{

	protected float mouseX;
	protected float mouseY;
	protected boolean leftButtonPressed;
	protected boolean rightButtonPressed;

	protected Mouse() {
        this.mouseX = 0;
		this.mouseY = 0;
	}

	public float getMouseX() {
		return mouseX;
	}

	public float getMouseY() {
		return mouseY;
	}

}

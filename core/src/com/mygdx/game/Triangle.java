package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Triangle extends Entity {
    private Color color;
    private float sideLength;
    public Triangle() {
        super(); // Default constructor
        this.color = null;
    }

    // Constructor with values assigned
    public Triangle(float x, float y, float speed, Color color, float sideLength) {
        super(x, y, speed);
        this.color = color;
        this.sideLength = sideLength;
    }

    public Color getColour() {
        return color;
    }

    public void setColour(Color color) {
        this.color = color;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(this.color);
        float x1 = this.getX() - sideLength;
        float x2 = this.getX();
        float x3 = this.getX() + sideLength;
        float y1 = this.getY() - sideLength;
        float y2 = this.getY() + sideLength;
        float y3 = this.getY() - sideLength;
        shape.triangle(x1, y1, x2, y2, x3, y3);
    }

    // Moves LR by default
    public void moveUserControlled() {
        float leftX = this.getX() - this.getSpeed() * Gdx.graphics.getDeltaTime();
        float rightX = this.getX() + this.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.A)) this.setX(leftX);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) this.setX(rightX);
    }

    public void moveAIControlled() {
        // not AI controlled yet
    }

    public void update() {
        System.out.printf("In a Triangle with %f-length sides at %f,%f position.\n", this.sideLength, this.getX(), this.getY());
    }
}

package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Entity {
    private Color color;
    private float radius;

    public Circle() {
        super(); // Default constructor
        this.color = null;
        this.radius = 0;
    }

    // Constructor with values assigned
    public Circle(float x, float y, float speed, float radius, Color color) {
        super(x, y, speed);
        this.radius = radius;
        this.color = color;
    }

    public Color getColour() {
        return color;
    }

    public void setColour(Color color) {
        this.color = color;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(this.color);
        shape.circle(this.getX(), this.getY(), this.radius);
    }

    // overrides default method of moving left right to up down
    public void moveUserControlled() {
        float upY= this.getY() + this.getSpeed() * Gdx.graphics.getDeltaTime();
        float downY = this.getY() - this.getSpeed() * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) this.setY(upY);
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) this.setY(downY);
    }

    public void moveAIControlled() {
    // not AI controlled
    }
    public void update() {
        System.out.printf("In a Circle with radius %f at %f,%f position.\n", this.getRadius(), this.getX(), this.getY());
    }
}

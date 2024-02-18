package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Enemy {
    private float radius;

    // Constructor with values assigned
    public Circle(float x, float y, float velocityX, float velocityY, Color color, float radius) {
        super(x, y, velocityX, velocityY, color);
        this.radius = radius;
    }

    protected float getRadius() {
        return this.radius;
    }

    protected void setRadius(float radius) {
        this.radius = radius;
    }

    protected void draw(ShapeRenderer shape) {
        shape.setColor(this.color);
        shape.circle(this.getX(), this.getY(), this.radius);
    }


    @Override
    protected void logConsole() {
        System.out.printf("In a Circle with radius %f at %f,%f position.\n", this.getRadius(), this.getX(), this.getY());
    }

    @Override
    protected void movement() {

    }
}

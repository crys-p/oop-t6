package com.mygdx.game.EntityManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Circle extends Enemy {
    private float radius;

    // Constructor with values assigned
    public Circle(float x, float y, float velocityX, float velocityY, Color color, float radius) {
        super(x, y, velocityX, velocityY, color);
        this.radius = radius;
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

    @Override
    public void inflictDamage(Player player) {
        System.out.println("I am inflicting Circle Damage on " + player + "\n");
    }

    @Override
    public void logConsole() {
        System.out.printf("In a Circle with radius %f at %f,%f position.\n", this.getRadius(), this.getX(), this.getY());
    }

    @Override
    public void movement(float deltaTime) {

    }
}

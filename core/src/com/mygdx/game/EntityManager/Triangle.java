package com.mygdx.game.EntityManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Triangle extends Enemy {

    private final float sideLength;

    // Constructor with values assigned
    public Triangle(float x, float y, float velocityX, float velocityY, Color color, float sideLength) {
        super(x, y, velocityX, velocityY, color);
        this.sideLength = sideLength;
    }


    @Override
    protected float getHeight() {
        return (float) ((Math.sqrt(3) / 2) * sideLength);
    }

    @Override
    protected float getWidth() {
        return sideLength;
    }

    protected void draw(ShapeRenderer shape) {
        shape.setColor(this.color);
        float x1 = this.getX() - sideLength;
        float x2 = this.getX();
        float x3 = this.getX() + sideLength;
        float y1 = this.getY() - sideLength;
        float y2 = this.getY() + sideLength;
        float y3 = this.getY() - sideLength;
        shape.triangle(x1, y1, x2, y2, x3, y3);
    }


    protected void logConsole() {
        System.out.printf("In a Triangle with %f-length sides at %f,%f position. My EntityID is %d\n", this.sideLength, this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {

        updateBoundingBox();
    }

}

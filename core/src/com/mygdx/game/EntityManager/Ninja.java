package com.mygdx.game.EntityManager;

class Ninja extends Enemy {

    // Constructor with values assigned
    protected Ninja(float x, float y, float velocityX, float velocityY, String image) {
        super(x, y, velocityX, velocityY, image);
    }


    protected void logConsole() {
        System.out.printf("In a Ninja at %f,%f position. My EntityID is %d\n", this.getX(), this.getY(), this.entityID);
    }

    @Override
    protected void movement() {
        float newX = aiControlManager.moveLR(this.getX());
        this.setX(newX);
    }

}

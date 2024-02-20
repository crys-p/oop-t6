package com.mygdx.game.AIControlManager;

public class AIControlManager {
    private float movementSpeed;
    private float jump;
    public void handleCollision() {}
    public float getMovementSpeed() { return movementSpeed; }
    public void setMovementSpeed(float movementSpeed) { this.movementSpeed = movementSpeed;}
    public void getDirection() {}
    public void setDirection() {}
    public void getAccelerate() {}
    public void setAccelerate() {}
    public void directionTurnDelay() {}
    public void moveToPosition() {}
//    public void jump() {
//        List<Character> characters = getCharacters();
//        if (!characters.isEmpty()) {
//            Character player = characters.get(0);
//            if (onGround) {
//                float newY = player.getEntityY() + jump;
//                player.setEntityY(newY);
//                onGround = false;
//            }
//        }
//    }
}

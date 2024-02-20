package com.mygdx.game.PlayerControlManager;

import com.mygdx.game.CollisionManager.CollisionManager;
import com.mygdx.game.EntityManager.EntityManager;

import java.util.List;

public class PlayerControlManager {
    private int health;
    private int maxHealth;
    private float movementSpeed;
    private float jump;
    private CollisionManager collisionManager;
    private boolean onGround;
    private final EntityManager entityManager;

    // Constructor to initialize EntityManager
    public PlayerControlManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    // Getter for characters
    public List<Character> getCharacters() {
        // return entityManager.getCharacters(); //character is package protected
        return null;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getMaxHealth() { return maxHealth; }
    public void setMaxHealth(int maxHealth) { this.maxHealth = maxHealth; }
    public float getMovementSpeed() { return movementSpeed; }
    public void setMovementSpeed(float movementSpeed) { this.movementSpeed = movementSpeed; }
    public float getJump() { return jump; }
    public void setJump(float jump) { this.jump = jump; }

    public float getPlayerX() {
        List<Character> characters = getCharacters();
        if (!characters.isEmpty()) {
//            return characters.get(0).getEntityX(); // comment out for compile testing - crys
        }
        return 0; // Return default value if no player character found
    }

    public float getPlayerY() {
        List<Character> characters = getCharacters();
        if (!characters.isEmpty()) {
//            return characters.get(0).getEntityY(); // comment out for compile testing - crys
        }
        return 0; // Return default value if no player character found
    }

    //to be clarified
//    public void moveLeft() {
//        List<Character> characters = getCharacters();
//        if (!characters.isEmpty() && collisionManager.canMoveLeft(characters.get(0))) {
//            Character player = characters.get(0);
//            float newX = player.getEntityX() - movementSpeed;
//            player.setEntityX(newX);
//        }
//    }
//
//    public void moveRight() {
//        List<Character> characters = getCharacters();
//        if (!characters.isEmpty() && collisionManager.canMoveRight(characters.get(0))) {
//            Character player = characters.get(0);
//            float newX = player.getEntityX() + movementSpeed;
//            player.setEntityX(newX);
//        }
//    }
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
    public void setOnGround(boolean onGround) { this.onGround = onGround; }
    public boolean isOnGround() { return onGround; }
}

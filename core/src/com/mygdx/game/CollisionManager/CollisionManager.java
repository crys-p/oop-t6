package com.mygdx.game.CollisionManager;

import com.mygdx.game.Config;
import com.mygdx.game.EntityManager.Entity;

public class CollisionManager {

    private CollisionManager collisionManager;
    public CollisionManager()
    {

    }

    public void Collided()
    {

    }

    //shermaine
    public boolean canMoveLeft(Entity player) {
        // Check if moving left would not go beyond the left boundary of the screen
        // You may also check for obstacles or collisions preventing movement left
        return player.getEntityX() > 0; // Assuming 0 is the left boundary of the screen
    }

    public boolean canMoveRight(Entity player) {
        // Check if moving right would not go beyond the right boundary of the screen
        // You may also check for obstacles or collisions preventing movement right
        return player.getEntityX() < Config.SCREEN_WIDTH; // Assuming screenWidth is the right boundary of the screen
    }

}

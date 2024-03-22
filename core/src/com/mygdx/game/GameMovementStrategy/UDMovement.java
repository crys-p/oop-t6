    package com.mygdx.game.GameMovementStrategy;

    import com.badlogic.gdx.Gdx;
    import com.mygdx.engine.MovementStrategy.AIMovement;
    import com.mygdx.engine.EntityManager.Entity;

    import static com.badlogic.gdx.math.MathUtils.random;
    import static com.mygdx.engine.SceneManager.SceneManager.SCENE_HEIGHT;

    public class UDMovement extends AIMovement {
        public UDMovement(Entity entity) {
            super(entity);
        }

        @Override
        public float[] calculateMovement(float entityX, float entityY,float speed) {
            float movementSpeed = 5; // Use provided speed
            float direction = 1; // Initial direction: up

            // Update position based on movement speed and direction
            float newY = entityY + (movementSpeed * direction);

            // Check if AI has reached the edge of the screen
            if (newY <= 0 || newY >= SCENE_HEIGHT - 40) {
                // Reverse the direction if the edge is reached
                direction *= -1;
                // Randomize X position
                entityX = random.nextFloat() * (Gdx.graphics.getWidth() - 100);
                // Adjust the position to keep the AI within screen bounds
                newY = Math.max(0, Math.min((float) Gdx.graphics.getHeight(), newY)) * direction;
            }

            // Return the new position
            return new float[] {entityX, newY};
        }
    }

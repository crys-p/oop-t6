    package com.mygdx.engine.AIControlManager;

    import com.badlogic.gdx.Gdx;
    import com.mygdx.engine.EntityManager.Entity;
    import com.mygdx.engine.Movement.Movement;

    import static com.badlogic.gdx.math.MathUtils.random;

    public class LRMovement extends AIMovement {
        public LRMovement(Entity entity) {
            super(entity);
        }

        @Override
        public float[] calculateMovement(float entityX, float entityY,float speed) {
            float movementSpeed = speed; // Initial movement speed
            float direction = 1; // Initial direction: right

            // Update position based on movement speed and direction
            float newX = entityX + (movementSpeed * direction);

            // Check if AI has reached the edge of the screen
            if (newX <= 0 || newX >= (float) Gdx.graphics.getWidth()) {
                // Reverse the direction
                direction *= -1;
                // Randomize Y position
                entityY = random.nextFloat() * (Gdx.graphics.getHeight() - 100);
                // Adjust the position to keep the AI within screen bounds
                newX = Math.max(0, Math.min((float) Gdx.graphics.getWidth(), newX)) * direction;
            }

            // Return the new position
            return new float[] {newX, entityY};
        }
    }

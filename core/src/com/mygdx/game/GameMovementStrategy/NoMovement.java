    package com.mygdx.game.GameMovementStrategy;

    import com.badlogic.gdx.Gdx;
    import com.mygdx.engine.EntityManager.Entity;
    import com.mygdx.engine.MovementStrategy.AIMovement;

    import static com.badlogic.gdx.math.MathUtils.random;

    public class NoMovement extends AIMovement {
        public NoMovement(Entity entity) {
            super(entity);
        }

        @Override
        public float[] calculateMovement(float entityX, float entityY, float speed) {
            // Return the new position
            return new float[] {entityX, entityY};
        }
    }

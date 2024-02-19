    package com.mygdx.game.SceneManager;

    import com.badlogic.gdx.Game;
    import com.badlogic.gdx.ScreenAdapter;
    import com.badlogic.gdx.graphics.g2d.SpriteBatch;
    import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
    import com.badlogic.gdx.utils.Timer;
    import com.badlogic.gdx.graphics.Color;
    import com.badlogic.gdx.Gdx;
    import com.mygdx.game.EntityManager.EntityManager;

    import java.util.Random;

    public class StartScene extends Scene {

        private SceneManager sceneManager;
        private EntityManager entityManager;

        public StartScene(Game game, SceneManager sceneManager,EntityManager entityManager) {
            super(game);
            setBackgroundColor(Color.GRAY); // Set background color for start scene
            this.sceneManager = sceneManager;
            this.entityManager = entityManager;

        }

        @Override
        public void show() {
            createEntities();


        }

        @Override
        protected void createEntities() {
            // Create entities specific to the start scene
            // Implement entity creation logic here
            entityManager.createCharacter(1, 100, -100, 20, 0);
            Random random = new Random();
            entityManager.createItemRandomX(10, random, 680, 0, 20);
            entityManager.createTriangle(1, 300, 200, 40, 40, Color.GREEN, 50);
            entityManager.createCircle(1, 200, 300, 400, 0, Color.RED, 50);
            entityManager.logAll(); // for debugging
        }

        // creation of entities specific to the start scene


        @Override
        public void hide() {
            // Logic when the start scene is hidden
        }

        @Override
        public void render(float delta) {
            // Rendering sprites and shapes
            Gdx.app.log("Start scene", "rendering!");

            // Clear the screen
            clearScreen();
            // Update the camera and viewport
            //camera.update();
        }

        @Override
        public void resize(int width, int height) {
            // Update the viewport
            viewport.update(width, height);

        }


    }

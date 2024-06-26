package com.mygdx.engine.CameraManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class Camera {
    protected OrthographicCamera camera = null;
    private final float SCALE = 1.0f;

    protected Camera(){}

    protected void Camera(float width, float height)
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width/SCALE, height/SCALE);
    }

    protected void cameraUpdate(float delta, Vector3 position)
    {
        camera.position.set(position);
        camera.update();
    }


}

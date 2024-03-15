package com.mygdx.engine.CameraManager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.engine.EntityManager.Entity;
import com.mygdx.engine.EntityManager.EntityManager;

import java.util.ArrayList;

public class Camera {
    public OrthographicCamera camera = null;
    private final float SCALE = 1.0f;

    protected Camera(){}

    protected void Camera(float width, float height)
    {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width/SCALE, height/SCALE);
    }

    public void cameraUpdate(float delta, Vector3 position)
    {
        camera.position.set(position);
        camera.update();
    }


}

package com.mygdx.game.CollisionManager;

import com.badlogic.gdx.math.Rectangle;

public class Collision {
    private Rectangle PlayerRect;
    private Rectangle EnemyRect;
    private Rectangle ObjectRect;
    private float prevx;
    private float prevy;
    private float Entx;
    private float Enty;
    public Collision()
    {

    }

    public void createPlayerRect()
    {

    }

    public void createEnemyRect()
    {

    }

    //@override
    public void collided()
    {
       // If(ObjectRect.overlaps(PlayerRect))
        {
            System.out.println("collided");
           // Entx.set(prevx);
            //Enty.set(prevy);
        }
    }

    public float getPrevx()
    {
        return prevx;
    }

    public float getPrevy()
    {
        return prevy;
    }

    public void setPrevx(float prevx)
    {
        this.prevx = prevx;
    }

    public void setPrevy(float prevy)
    {
        this.prevy = prevy;
    }
}

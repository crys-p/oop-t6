package com.mygdx.game.CollisionManager;

import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.EntityManager.EntityManager;

public class Collision {
    private Rectangle PlayerRect;
    private Rectangle EnemyRect;
    private Rectangle ObjectRect;
    private float prevx;
    private float prevy;
    private float Entx;
    private float Enty;

    public Collision(EntityManager entityManager) {
        super();
    }

    //public Collision(EntityManager entityManager) {
    //    super(entityManager);
    //}

    public void createPlayerRect(float x, float y, float width, float height)
    {
        PlayerRect = new Rectangle(x,y,width,height);
    }

    public void createPlayerRect()
    {
        PlayerRect = new Rectangle(100,100,100,100);
    }

    public void createEnemyRect()
    {
        EnemyRect = new Rectangle(100,100,100,100);
    }

    public void createObjectRect()
    {
        ObjectRect = new Rectangle(100,100,100,100);
    }

    public void collided()
    {
        if(ObjectRect.overlaps(PlayerRect))
        {
            System.out.println("collided");
            Entx = prevx;
            Enty = prevy;
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

    public Rectangle getPlayerRect()
    {
        return PlayerRect;
    }

    public Rectangle getEnemyRect()
    {
        return EnemyRect;
    }

    public Rectangle getObjectRect()
    {
        return ObjectRect;
    }

    public void setPrevx(float prevx)
    {
        this.prevx = prevx;
    }

    public void setPrevy(float prevy)
    {
        this.prevy = prevy;
    }

    public void setPlayerRect(Rectangle Rect)
    {
        this.PlayerRect = Rect;
    }

    public void setEnemyRect(Rectangle Rect)
    {
        this.EnemyRect = Rect;
    }

    public void setObjectRect(Rectangle Rect)
    {
        this.ObjectRect = Rect;
    }
}

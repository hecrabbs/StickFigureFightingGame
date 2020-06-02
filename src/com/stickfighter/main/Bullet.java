package com.stickfighter.main;

import java.awt.*;

public class Bullet extends GameObject{
    //private int x,y,w,h;
    //private int xVel,yVel;

    public Bullet(int x, int y, ID id){
        super(x,y,id);
        this.width=5;
        this.height=5;
        // x should be player.getX() and y=player.getY().
        /*this.x= (int) p.getX();
        this.y=(int) p.getY()/2;
        this.w=5;
        this.h=5;*/
    }

    public void tick() {
        this.x+=this.velX;
    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        //g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(this.getBulletBounds());

    }
    public Bullet shoot() {
        //ammo--;
        //shooting=true;
        Bullet b=new Bullet((int) this.x,(int) this.y/2,ID.Bullet);
        if(isFacingRight()){
            //Bullet b=new Bullet(p);
            b.setVelocity(5);
            return b;
        } else{
            //Bullet b=new Bullet(p);
            b.setVelocity(-5);
            return b;
        }
        //return null;
    }

    public Rectangle getBulletBounds() {
        return new Rectangle((int)this.x, (int)this.y, 5, 5);
    }

    public void setVelocity(int xVel) {
        this.velX=xVel;
    }

}

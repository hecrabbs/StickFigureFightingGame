package com.stickfighter.main;

import java.awt.*;
import java.util.LinkedList;

public class Bullet extends GameObject{
    private Handler handler;
    private int initialPos;

    public Bullet(int x, int y, Handler handler, ID id){
        super(x,y,id);
        this.width=5;
        this.height=5;
        this.handler=handler;
        this.initialPos=x;
        // x should be player.getX() and y=player.getY().
        /*this.x= (int) p.getX();
        this.y=(int) p.getY()/2;
        this.w=5;
        this.h=5;*/
    }

    public void tick() {
        this.x+=this.velX;
        bulletCollision(Game.gameObjects);
    }

    public void render(Graphics g){
        g.setColor(Color.BLACK);
        //g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        Graphics2D g2d = (Graphics2D) g;
        g2d.draw(this.getBulletBounds());

    }

    public void bulletCollision(LinkedList<GameObject> object) {
        for (int i=0;i<handler.gameObjects.size() && object.get(i).getID()!=ID.Bullet;i++){
            GameObject temp = object.get(i);
            if(this.getBulletBounds().intersects(temp.getBounds()) && temp.getID()==ID.Platform){
                handler.removeObject(this);
                System.out.println("Hit wall");
            }
            // I think we only need to check one collision for bullets,
            // because enemies will get knocked back in the opposite direction
            // of their velocities no matter what. Double check to make sure this is correct.
            if(this.getBulletBounds().intersects(temp.getBounds())){
                if(temp.getID()==ID.Enemy){
                    temp.health=0;
                    //temp.knockback=true;
                    if(this.initialPos<temp.getX()){
                        temp.setVelX((int) -2 * temp.getVelX());
                        temp.setKnockback();
                        //System.out.println("Left");
                    }
                    else{
                        temp.setVelX((int) 2 * temp.getVelX());
                        temp.setKnockback();
                        //System.out.println("Right");
                    }
                    //temp.setKnockback();
                    temp.setVelY(-10);
                    System.out.println("Hit Enemy");
                    handler.removeObject(this);
                }
            }
        }
        if(this.getX()>(this.initialPos+700) || this.getX()<(this.initialPos-700)){
            handler.removeObject(this);
        }
    }

    public Rectangle getBulletBounds() {
        return new Rectangle((int)this.x, (int)this.y, 5, 5);
    }

    public void setVelocity(int xVel) {
        this.velX=xVel;
    }

}

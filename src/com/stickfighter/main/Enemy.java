package com.stickfighter.main;

import com.stickfighter.graphics.Assets;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {

    private GameObject player;
    private Handler handler;

    public Enemy(int x, int y, Handler handler, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
        this.width = 32;
        this.height = 64;
    }

    public void tick() {
        x += velX;
        y += velY;
        if (this.falling || this.jumping) {
            velY += gravity;
        }
        enemyCollision(Game.gameObjects);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.RED);
        g.setColor(Color.RED);
        //enemy's rectangle
        g.fillRect((int)this.x, (int)this.y, this.width, this.height);

        g.setColor(Color.WHITE);
        //hitbox rectangles
        g2d.draw(this.getBoundsB());
        g2d.draw(this.getBoundsT());
        g2d.draw(this.getBoundsL());
        g2d.draw(this.getBoundsR());
    }

    public void enemyCollision(LinkedList<GameObject> object){
        for(int i=0;i<handler.gameObjects.size();i++){
            GameObject temp = object.get(i);
            if(temp.getID()!=ID.Enemy){//temp.getID()==ID.Platform || temp.getID()==ID.Player){
                //this checks to see if the player object is overlapping the object in question
                //System.out.println(temp.getID());
                if(this.getBoundsB().intersects(temp.getBounds())){//Bottom intersection
                    //System.out.println("Boom");
                    this.velY=0;
                    this.y = temp.getY()-this.height;
                    falling=false;jumping=false;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
                if(this.getBoundsT().intersects(temp.getBounds())){//Top intersection
                    //This is a little buggy, you can get pushed through the floor
                    //and possibly other objects if there is something on top of the player
                    this.y = temp.getY()+temp.height;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
                if(this.getBoundsL().intersects(temp.getBounds())){//Left intersection
                    //I'm pretty sure this is a little buggy, could be the right hand side
                    //the issue is the same as the top bounds. Could potentially push you through
                    //a boundary of some kind.
                    this.x = temp.getX() + temp.width;
                    /*if(temp.getID()==ID.Player) {
                        this.x=temp.getX() + temp.width+10;
                    }
                    else{
                        this.x=temp.getX() + temp.width;
                    }*/
                }
                if(this.getBoundsR().intersects(temp.getBounds())){//Right intersection
                    this.x = temp.getX() - this.width;
                    /*if(temp.getID()==ID.Player) {
                        this.x = temp.getX() - this.width-10;
                    }
                    else{
                        this.x = temp.getX() - this.width;
                    }*/
                }
            }

        }
    }

}
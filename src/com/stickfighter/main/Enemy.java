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
        //follow(this.player, delta);
        x += velX;
        y += velY;
        velX=0;
        velY += 1.2;
        /*if(this.getBounds().intersects(player.getBounds())) {
            this.velX = 0;
            //this.velY = 0;
        }
        else {
            this.velX = 3;
            //this.velY = 3;
        }*/
        enemyCollision(Game.gameObjects);
    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.width, this.height);

        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        g2d.draw(getEBoundsB());
        g2d.draw(getEBoundsTop());
        g2d.draw(getEBoundsL());
        g2d.draw(getEBoundsR());
    }

    //This gets the bottom bounds of the rectangle
    public Rectangle getEBoundsB(){
        return new Rectangle (this.x+(this.width/2)-((this.width/2)/2),this.y+(this.height/2),this.width/2,this.height/2);
    }
    //This gets the top bounds of the rectangle
    public Rectangle getEBoundsTop(){
        return new Rectangle (this.x+(this.width/2)-((this.width/2)/2),this.y,this.width/2,this.height/2);
    }
    //This gets the right bounds of the rectangle
    public Rectangle getEBoundsR(){
        return new Rectangle (this.x+this.width-5,this.y+5,5,this.height-10);
    }
    //This gets the left bounds of the rectangle
    public Rectangle getEBoundsL(){
        return new Rectangle (this.x,this.y+5,5,this.height-10);
    }

    public void enemyCollision(LinkedList<GameObject> object){
        for(int i=0;i<handler.gameObjects.size();i++){
            GameObject temp = object.get(i);
            if(temp.getID()==ID.Platform){
                //this checks to see if the player object is overlapping the object in question
                if(getEBoundsB().intersects(temp.getBounds())){//Bottom intersection
                    //System.out.println("Boom");
                    this.velY=0;
                    this.y = temp.getY()-this.height;
                    falling=false;jumping=false;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
                else if(getEBoundsTop().intersects(temp.getBounds())){//Top intersection
                    //This is a little buggy, you can get pushed through the floor
                    //and possibly other objects if there is something on top of the player
                    this.y = temp.getY()+temp.height;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
                else if(getEBoundsL().intersects(temp.getBounds())){//Left intersection
                    //I'm pretty sure this is a little buggy, could be the right hand side
                    //the issue is the same as the top bounds. Could potentially push you through
                    //a boundary of some kind.
                    this.x = temp.getX() + temp.width;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
                else if(getEBoundsR().intersects(temp.getBounds())){//Right intersection
                    this.x = temp.getX() - this.width;
                    /*if(temp.getID()==ID.Enemy) {
                        health--;
                    }*/
                }
            }

        }
    }

}
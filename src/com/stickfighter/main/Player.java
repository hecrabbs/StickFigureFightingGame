package com.stickfighter.main;

import com.stickfighter.graphics.Assets;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private Handler handler;
    public static int health = 100;




    public Player(int x, int y, Handler handler, ID id) {
        super(x, y, id);
        this.handler = handler;
        this.width = 32;
        this.height = 64;
    }

    public void tick(double dt) {
        x += velX;
        y += velY;
        velY += 1.2;

        //check collisions
        collision(Game.gameObjects);
    }

    public void render(Graphics g, double delta) {
        g.setColor(Color.WHITE);
        g.fillRect(this.x, this.y, this.width, this.height);
        //The following lines basically show the points where
        //a collision will be detected.
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.RED);
        g2d.draw(getBoundsBottom());
        g2d.draw(getPBoundsTop());
        g2d.draw(getPBoundsL());
        g2d.draw(getPBoundsR());
        g.drawImage(Assets.playerRight[0], this.x-40, this.y-40, null);
    }

    //This gets the bottom bounds of the rectangle
    public Rectangle getBoundsBottom(){
        return new Rectangle (this.x+(this.width/2)-((this.width/2)/2),this.y+(this.height/2),this.width/2,this.height/2);
    }
    //This gets the top bounds of the rectangle
    public Rectangle getPBoundsTop(){
        return new Rectangle (this.x+(this.width/2)-((this.width/2)/2),this.y,this.width/2,this.height/2);
    }
    //This gets the right bounds of the rectangle
    public Rectangle getPBoundsR(){
        return new Rectangle (this.x+this.width-5,this.y+5,5,this.height-10);
    }
    //This gets the left bounds of the rectangle
    public Rectangle getPBoundsL(){
        return new Rectangle (this.x,this.y+5,5,this.height-10);
    }

    public void collision(LinkedList<GameObject> object){
        for(int i=0;i<handler.gameObjects.size();i++){
            GameObject temp = object.get(i);
            if(temp.getID()==ID.Platform || temp.getID()==ID.Enemy){
                //this checks to see if the player object is overlapping the object in question
                if(getBoundsBottom().intersects(temp.getBoundsBottom())){//Bottom intersection
                    //System.out.println("Boom");
                    this.velY=0;
                    this.y = temp.getY()-this.height;
                    falling=false;jumping=false;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }
                else if(getPBoundsTop().intersects(temp.getBoundsBottom())){//Top intersection
                    //This is a little buggy, you can get pushed through the floor
                    //and possibly other objects if there is something on top of the player
                    this.y = temp.getY()+temp.height;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }
                else if(getPBoundsL().intersects(temp.getBoundsBottom())){//Left intersection
                    //I'm pretty sure this is a little buggy, could be the right hand side
                    //the issue is the same as the top bounds. Could potentially push you through
                    //a boundary of some kind.
                    this.x = temp.getX() + temp.width;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }
                else if(getPBoundsR().intersects(temp.getBoundsBottom())){//Right intersection
                    this.x = temp.getX() - this.width;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }
            }

        }
    }



}
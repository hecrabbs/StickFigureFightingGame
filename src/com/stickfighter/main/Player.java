package com.stickfighter.main;

import com.stickfighter.enumStates.GameState;
import com.stickfighter.graphics.Animation;
import com.stickfighter.graphics.Assets;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private Handler handler;
    public static int health = 100;
    private Animation playerRight;
    private Animation playerLeft;

    public Player(int x, int y, Handler handler, ID id) {
        super(x, y, id);
        this.handler = handler;
        this.width = 32;
        this.height = 64;
        this.movingLeft = false;
        this.movingRight = false;
        this.falling = true;
        this.jumping = false;
        this.knockback = false;

        playerRight = new Animation(5, Assets.playerRight);
        playerLeft = new Animation(5, Assets.playerLeft);
    }

    public void tick() {
        x += velX ;
        y += velY;
        if (falling || jumping) {
            velY += gravity;
        }
        collision(Game.gameObjects);

        playerRebound();
        isAlive();
        if (velX > 0) {
            playerRight.runAnimation();
        } else if (velX < 0) {
            playerLeft.runAnimation();
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        //player's rectangle
        g.fillRect((int)this.x, (int)this.y, this.width, this.height);

        g.setColor(Color.RED);
        //hitbox rectangles
        g2d.draw(this.getBoundsB());
        g2d.draw(this.getBoundsT());
        g2d.draw(this.getBoundsL());
        g2d.draw(this.getBoundsR());

        if(velX > 0) {
            playerRight.drawAnimation(g, (int) x-25, (int) y-25, 100, 100);
        } else if (velX < 0) {
            playerLeft.drawAnimation(g, (int) x-25, (int) y-25, 100, 100);
        } else
         {
            g.drawImage(Assets.playerRight[0], (int) x-25, (int) y-25, 100, 100, null);
        }
    }

    public void isAlive(){
        if(health<=0){
            Game.setState(GameState.GameOver);
        }
    }

    public void collision(LinkedList<GameObject> object){
        for(int i=0;i<handler.gameObjects.size();i++){
            GameObject temp = object.get(i);
            if(temp.getID()==ID.Platform || temp.getID()==ID.Enemy){
                //this checks to see if the player object is overlapping the object in question
                if(this.getBoundsB().intersects(temp.getBounds())){//Bottom intersection
                    this.y = temp.getY()-this.height;
                    this.velY=0;
                    this.falling = false;
                    this.jumping = false;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }
                else {
                    falling = true;
                }

                if(this.getBoundsT().intersects(temp.getBounds())){//Top intersection
                    //This is a little buggy, you can get pushed through the floor
                    //and possibly other objects if there is something on top of the player
                    this.y = temp.getY() + temp.height;
                    this.velY = 0;
                    if(temp.getID()==ID.Enemy) {
                        health--;
                    }
                }

                if(this.getBoundsL().intersects(temp.getBounds())){
                    this.x = temp.getX() + temp.width;
                    if(temp.getID()==ID.Enemy) {
                        velY = -10;
                        velX = 15;
                        knockback = true;
                        health--;
                    }
                }

                if(this.getBoundsR().intersects(temp.getBounds())){//Right intersection
                    this.x = temp.getX() - this.width;
                    if(temp.getID()==ID.Enemy) {
                        velY = -10;
                        velX = -15;
                        knockback = true;
                        health--;
                    }
                }
            }

        }
    }

    //Depending on the number given, the player will
    public void playerRebound(){
        if (knockback) {
           velX -= (float) velX/(1+20);
            if (Math.abs(velX) <= 5) {
                velX = 0;
                knockback = false;
                this.movingRight = false;
                this.movingLeft = false;
            }
        }
    }

    public void playerAttack(){

    }

}
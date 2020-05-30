package com.stickfighter.main;

import com.stickfighter.graphics.Assets;

import java.awt.*;
import java.util.LinkedList;

public class Enemy extends GameObject {

    private GameObject player;
    private Handler handler;
    private int rangeLeft,rangeRight;

    public Enemy(int x, int y, Handler handler, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
        this.width = 32;
        this.height = 64;
        this.health=32;
        this.rangeLeft=95;this.rangeRight=198;
    }

    public void tick() {
        //velX=-4;
        x += velX;
        y += velY;
        if (this.falling || this.jumping) {
            velY += gravity;
        }
        enemyCollision(Game.gameObjects);
        enemyRebound();
        enemyRange();
        //this.follow(this.player);
        //System.out.println("Enemy Falling: "+this.falling);
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
        enemyHealth(g);
        }

    public void enemyCollision(LinkedList<GameObject> object){
        for(int i=0;i<handler.gameObjects.size();i++){
            GameObject temp = object.get(i);
            if(temp.getID()==ID.Platform || temp.getID()==ID.Player){
                //this checks to see if the player object is overlapping the object in question
                if(this.getBoundsB().intersects(temp.getBounds())){//Bottom intersection
                    //System.out.println("Boom");
                    this.velY=0;
                    this.y = temp.getY()-this.height;
                    falling=false;jumping=false;
                } else { this.falling=true; }
                if(this.getBoundsT().intersects(temp.getBounds())){//Top intersection
                    this.y = temp.getY()+temp.height;
                }
                if(this.getBoundsL().intersects(temp.getBounds())){//Left intersection
                    this.x = temp.getX() + temp.width;
                    if(temp.getID()==ID.Player) {
                        velY = -10;
                        velX = 15;
                        this.knockback=true;
                        this.health--;
                        if(temp.getVelX()==0 && temp.isAttacking){
                            //temp.setVelX(-15);
                            //temp.setVelY(-10);
                            System.out.println("Kapow");
                            this.x = (float) temp.strikeRange.getX() + (float) temp.strikeRange.getWidth();
                            temp.knockback=false;
                        }
                        else if(temp.getVelX()==0 && !temp.isAttacking){
                            System.out.println("Kapow2");
                            temp.setVelX(-15);
                            temp.setVelY(-10);
                            Player.health--;
                            temp.knockback=true;
                        }
                    }
                }
                if(this.getBoundsR().intersects(temp.getBounds())){//Right intersection
                    this.x = temp.getX() - this.width;
                    //System.out.println("Boom!!!!");
                    if(temp.getID()==ID.Player) {
                        velY = -10;
                        velX = -15;
                        this.knockback=true;
                        this.health--;
                        if(temp.getVelX()==0 && temp.isAttacking){
                            //temp.setVelX(15);
                            //temp.setVelY(-10);
                            temp.knockback=false;
                        }
                        if(temp.getVelX()==0 && !temp.isAttacking){
                            temp.setVelX(15);
                            temp.setVelY(-10);
                            Player.health--;
                            temp.knockback=true;
                        }
                    }
                }
            }
            //else{System.out.println("working");}

        }
    }

    public void enemyHealth(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int) this.x,(int) this.y-20, width, 7);
        g.setColor(Color.getHSBColor( (1f * this.health) / 360, 1f, 1f));
        g.fillRect((int) this.x,(int) this.y-20, this.health, 7);
        g.setColor(Color.WHITE);
        g.drawRect((int) this.x,(int) this.y-20, width, 7);
    }
    //public int getEnemyHealth(){ return health; }
    //public void setEnemyHealth(int health){ this.health=health; }

    public void enemyRebound(){
        if(this.knockback){
            velX -= (float) velX/(1+20);
            if (Math.abs(velX) <= 2) {
                velX = 0;
                this.knockback = false;
                this.movingRight = false;
                this.movingLeft = false;
            }
        }
    }

    public void enemyRange(){
        if(!knockback) {
            if (this.x == this.rangeLeft) {
                velX = 5;
                //this.x+=velX;
                System.out.println("left");
            } else if (this.x == this.rangeRight+100) {
                velX = 5;
            } else {
                velX = -5;
            }
        }
    }

}
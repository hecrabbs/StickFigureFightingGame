package com.stickfighter.main;

import com.stickfighter.enumStates.StateID;
import com.stickfighter.graphics.Animation;
import com.stickfighter.graphics.Assets;


import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject {
    private Handler handler;
    public static int health = 100;
    private Animation moveR, moveL, attackR, attackL, idle;
    private int initX,initY;
    //private boolean hasGun,shooting;

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
        this.facingRight = true;
        this.hasGun=true;//Set false later
        this.ammo=1000;

        moveR = new Animation(.06f, Assets.pMoveR);
        moveL = new Animation(.06f, Assets.pMoveL);
        attackR = new Animation(100, Assets.pAttackR);
        attackL = new Animation(100, Assets.pAttackL);
        idle = new Animation(100, Assets.pIdle);
    }

    public void tick() {
        this.x += this.velX;
        this.y += this.velY;
        if (this.falling || this.jumping) {
            this.velY += gravity;
        }
        collision(Game.gameObjects);
        playerRebound();
        isAlive();
        moveR.tick();
        moveL.tick();
        attackR.tick();
        attackL.tick();
        idle.tick();
        if (this.isAttacking) {
            hitEnemy(Game.gameObjects);
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(Color.WHITE);
        //player's rectangle
        g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        g.setColor(Color.RED);
        //hitbox rectangles
        g2d.draw(this.getBoundsB());
        g2d.draw(this.getBoundsT());
        g2d.draw(this.getBoundsL());
        g2d.draw(this.getBoundsR());

        if (this.isAttacking) {
            if (facingRight) {
                attackR.render(g, (int) x, (int) y, 64, 64);
            } else {
                attackL.render(g, (int) x, (int) y, 64, 64);
            }
            if (attackR.done || attackL.done) {
                this.isAttacking = false;
                attackR.done = false;
                attackL.done = false;
            }
//            g2d.draw(this.playerLightAttack());
        } else if (jumping) {
            if (facingRight) {
                g.drawImage(Assets.pMoveR[7], (int) x - 15, (int) y, 64, 64, null);
            } else {
                g.drawImage(Assets.pMoveL[7], (int) x - 15, (int) y, 64, 64, null);
            }
        } else if (velX != 0) {
            if (facingRight) {
                moveR.render(g, (int) x, (int) y, 64, 64);
            } else {
                moveL.render(g, (int) x, (int) y, 64, 64);
            }
        } else {
            idle.render(g, (int) x, (int) y, 64, 64);
        }
    }

    public void isAlive() {
        if (health <= 0) {
            Game.setState(StateID.GameOver);
        }
    }

    public void revive() {
        health = 75;
    }

    public void collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.gameObjects.size() /*&& object.get(i).getID()!=ID.Bullet*/; i++) {
            // The conditional below seems silly, but I think it is necessary because until
            // a fire rate is added into the game, bullets can continuously be added
            // which might be the source of the Index out of Bounds Exception we have
            // been getting. I will implement this in EnemyCollision as well to see.
            if(i>=handler.gameObjects.size()){
                i=handler.gameObjects.size()-1;
            }
            GameObject temp = object.get(i);
            if (temp.getID() == ID.Platform || temp.getID() == ID.Enemy) {
                //this checks to see if the player object is overlapping the object in question
                if (this.getBoundsB().intersects(temp.getBounds())) {//Bottom intersection
                    this.y = temp.getY() - this.height;
                    this.velY = 0;
                    this.falling = false;
                    this.jumping = false;
                    if (temp.getID() == ID.Enemy) {
                        health--;
                    }
                }if (!this.getBoundsB().intersects(temp.getBounds())) {
                    this.falling = true;
                }
                //else { this.falling = true; }
                //Top intersection
                if (this.getBoundsT().intersects(temp.getBounds())) {
                    this.y = temp.getY() + temp.height;
                    this.velY = 0;
                    if (temp.getID() == ID.Enemy) {
                        health--;
                    }
                }

                if (this.getBoundsL().intersects(temp.getBounds())) {
                    //System.out.println("Player Right1");
                    this.x = temp.getX() + temp.width;
                    if (temp.getID() == ID.Enemy) {
                        this.initX = (int) this.x;
                        this.initY = (int) this.y;
                        velY = -10;
                        velX = 15;
                        temp.setKnockback();
                        temp.setVelX(-15);
                        temp.setVelY(-10);
                        knockback = true;
                        System.out.println("Player Left");
                        health--;
                    }
                }

                if (this.getBoundsR().intersects(temp.getBounds())) {//Right intersection
                    this.x = temp.getX() - this.width;
                    //System.out.println("Player Right1");
                    if (temp.getID() == ID.Enemy) {
                        this.initX = (int) this.x;
                        this.initY = (int) this.y;
                        velY = -10;
                        velX = -15;
                        temp.setKnockback();
                        temp.setVelX(15);
                        temp.setVelY(-10);
                        this.knockback = true;
                        //System.out.println("Player Right");
                        health--;
                    }
                }
            }
        }
    }

    public void playerRebound() {
        if (knockback) {
            velX -= velX / (1 + 20);
            if (Math.abs(velX) <= 5) {
                velX = 0;
                knockback = false;
                this.movingRight = false;
                this.movingLeft = false;
            }
        }
    }

    public void checkLeft(){
        for(int i=0;i<handler.gameObjects.size() && handler.gameObjects.get(i).getID()==ID.Platform;i++){
            GameObject temp = Game.gameObjects.get(i);
            if (this.getBoundsL().intersects(temp.getBounds())){
                this.x = temp.getX() + temp.width;
                velX=0;
                knockback=false;
            }
            if(this.getBoundsL().intersects(temp.getBounds()) && this.getBoundsT().intersects(temp.getBounds())){
                if(this.initX>this.x){
                    //this.x = temp.getX() - this.width;
                    this.x = temp.getX() + temp.width;
                    System.out.println("Wagan1");
                    velX=0;
                    knockback=false;
                }
                if(this.initX<this.x){
                    //this.x = temp.getX() + temp.width;
                    this.x = temp.getX() - this.width;
                    System.out.println("Wagan2");
                    velX=0;
                    knockback=false;
                }
            }
            if(this.getBoundsB().intersects(temp.getBounds()) && this.getBoundsL().intersects(temp.getBounds())){
                if(this.initY>this.y){
                    //this.x = temp.getX() - this.width;
                    this.y = temp.getY() - this.height;
                    System.out.println("Bummer");
                    velX=0;
                    knockback=false;
                }
                if(this.initY<this.y){
                    //this.x = temp.getX() + temp.width;
                    System.out.println("Bummer2");
                    this.y = temp.getY() + temp.height;
                    velX=0;
                    knockback=false;
                }
            }
        }
    }

    public Rectangle playerLightAttack() {
        /*TODO: If the player holds down the 'J' key they can always be attacking. This needs to be fixed.
              The reach of the player does not really matter at this moment. We need to make sure that
              upon full extension of the player's reach we attack the bad guy; currently, the bad guy does
              get hit/damaged and the player receives no damage, but only when he comes into contact with
              the player.
        */
        if (facingRight) {
            Rectangle attackR = new Rectangle((int) this.x + 32, (int) this.y + 20, 42, 10);
            strikeRange = attackR;
            return attackR;
        } else {
            Rectangle attackL = new Rectangle((int) this.x - 42, (int) this.y + 20, 42, 10);
            strikeRange = attackL;
            return attackL;
        }
    }

    public void hitEnemy(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            if (object.get(i).getID() == ID.Enemy) {
                GameObject temp = object.get(i);
                if (this.playerLightAttack().getBounds().intersects(temp.getBounds())) {
                    this.knockback = false;
                    temp.knockback = true;
                    if (temp.getX() > this.x) {//knocks to the right side
                        temp.x = (float) ((strikeRange.getX() + strikeRange.getWidth()));
                        //System.out.println("hit right: "+strikeRange.getX()+"+"+strikeRange.getWidth());
                    }
                    if (temp.getX() < this.x) {//knocks to the left side
                        temp.x = (float) (this.x - 84);
                    }
                    temp.setVelY(-10);
                    temp.setVelX((((int) temp.getVelX() >> 1) + temp.getVelX()) * -1);
                    temp.health -= 8;
                }
            }
        }
    }

    public void shoot() {// maybe we do not need this at all? Maybe use it to check ammo?
        //ammo--;
        //shooting=true;
        Bullet b=new Bullet((int) this.getX(),(int) this.getY()+32,this.handler,ID.Bullet);
        if(this.isFacingRight()){
            b.setX(b.getX()+32);
            //b.setVelocity(16);
        } else{
            b.setVelocity(-16);
        }
        //return null;
    }

    public boolean canShoot(){
        if(hasGun && ammo>0){
            return true;
        }
        else{
            return false;
        }
    }


}
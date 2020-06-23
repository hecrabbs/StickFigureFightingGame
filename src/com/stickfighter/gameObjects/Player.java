package com.stickfighter.gameObjects;

import com.stickfighter.enumStates.LevelHandler;
import com.stickfighter.enumStates.StateID;
import com.stickfighter.graphics.Animation;
import com.stickfighter.graphics.Assets;
import com.stickfighter.main.Game;
import com.stickfighter.main.ID;

import java.awt.*;

public class Player extends GameObject {
    private final Animation moveR, moveL, attackR, attackL, idle;
    private int playerSpeed = 8;
    //private boolean hasGun,shooting;


    public Player(int x, int y, ID id) {
        super(x, y, id);
        this.width = 32;
        this.height = 64;
        setRectCollider();
        this.health = 100;
        this.facingRight = true;
        this.hasGun = true;//Set false later
        this.ammo = 1000;

        moveR = new Animation(.06f, Assets.pMoveR);
        moveL = new Animation(.06f, Assets.pMoveL);
        attackR = new Animation(100, Assets.pAttackR);
        attackL = new Animation(100, Assets.pAttackL);
        idle = new Animation(100, Assets.pIdle);

        falling = true;
        jumping = false;
        fixed = false;
        knockback = false;
        movingRight = false;
        movingLeft = false;
    }

    public void tick(Double dt) {
        addGravity();
        updatePosition();
        checkCollision();
        rebound();
        isAlive();
        moveR.tick();
        moveL.tick();
        attackR.tick();
        attackL.tick();
        idle.tick();
        if (this.attacking) {
            hitEnemy();
        }
        if (this.shooting) {
            shoot();
        }
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g.setColor(Color.WHITE);
//        //player's rectangle
//        g.fillRect((int) this.x, (int) this.y, this.width, this.height);
        g.setColor(Color.RED);
        //old hitbox rectangles
//        g2d.draw(this.getBoundsB());
//        g2d.draw(this.getBoundsT());
//        g2d.draw(this.getBoundsL());
//        g2d.draw(this.getBoundsR());
        //new hitbox shape
        g2d.draw(pathPoly);

        if (this.attacking) {
            if (facingRight) {
                attackR.render(g, (int) x, (int) y, 64, 64);
            } else {
                attackL.render(g, (int) x, (int) y, 64, 64);
            }
            if (attackR.done || attackL.done) {
                this.attacking = false;
                attackR.done = false;
                attackL.done = false;
            }
            g2d.draw(getAttackBounds());
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

    public void handleCollisions(Rectangle r, Rectangle oldR, GameObject temp) {
        if (temp.id == ID.Enemy) {
            health--;
            if (oldR.x >= temp.x + temp.width) {//left
                x = temp.x + temp.width;
                velY = -10;
                velX = 15;
                knockback = true;
                temp.velX = -10;
                temp.knockback = true;
            } else if (oldR.x + width <= temp.x) {//right
                x = temp.x - width;
                velY = -10;
                velX = -15;
                knockback = true;
                temp.velX = 10;
                temp.knockback = true;
            } else if (oldR.y + oldR.height <= temp.y) { //bottom
                y = temp.y - height;
                velY = 0;
                falling = false;
                jumping = false;
            } else if (oldR.y >= temp.y + temp.height) {//top
                y = temp.y + temp.height;
                velY = 0;
            }
        } else if (temp.id == ID.Flag) {
            Game.levelHandler.renderNextLevel();
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

    public void hitEnemy() {
        /*TODO: If the player holds down the 'J' key they can always be attacking. This needs to be fixed.
              The reach of the player does not really matter at this moment. We need to make sure that
              upon full extension of the player's reach we attack the bad guy; currently, the bad guy does
              get hit/damaged and the player receives no damage, but only when he comes into contact with
              the player.
        */
        for (int i = 0; i < handler.size(); i++) {
            GameObject temp = handler.get(i);
            if (temp.id == ID.Enemy) {
                if (getAttackBounds().intersects(temp.getBounds())) {
                    temp.health -= 8;
                    if (temp.x > x + width) {
                        temp.velX = 15;
                    } else temp.velX = -15;
                    temp.velY = -10;
                    temp.knockback = true;
                }
            }
        }
    }

    public void shoot() {// maybe we do not need this at all? Maybe use it to check ammo?
        //ammo--;
        //shooting=true;
        Bullet b = new Bullet((int) this.getX(), (int) this.getY() + 32, this.handler, ID.Bullet);
        if (this.isFacingRight()) {
            b.setX(b.getX() + 32);
            //b.setVelocity(16);
        } else {
            b.setVelocity(-16);
        }
        //return null;
    }

    public boolean canShoot() {
        return hasGun && ammo > 0;
    }

}
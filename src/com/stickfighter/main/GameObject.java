package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected int centerX, centerY;
    protected int width, height;
    protected ID id;
    protected int velX, velY;
    protected Rectangle rect;
    protected boolean falling = true, jumping = false;
    protected boolean knockback;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, width, height);
    };

    public void updateCenter() {
        this.centerX = this.x + this.width/2;
        this.centerY = this.y + this.height/2;
    }

//    public void follow(GameObject gameObject, double delta) {
//        this.updateCenter();
//        gameObject.updateCenter();
//        int dx = this.centerX - gameObject.centerX;
//        int dy = this.centerY - gameObject.centerY;
//        double direction;
//        if (dx == 0 && this.centerY > gameObject.centerY) {
//            direction = -Math.PI / 2;
//        } else if (dx == 0 && this.centerY < gameObject.centerY) {
//            direction = Math.PI / 2;
//        } else {
//            direction = Math.atan(((double) dy / (double) dx));
//        }
//
//        if (this.centerX > gameObject.centerX) {
//            direction -= Math.PI;
//        }
//
//        this.centerX += (this.velX * Math.cos(direction) * delta);
//        this.centerY += (this.velY * Math.sin(direction) * delta);
//        this.x = this.centerX + this.width/2;
//        this.y = this.centerY + this.height/2;
//    }

    public void follow(GameObject gameObject) {
        if (this.x >= gameObject.x + gameObject.width) {
            this.x -= this.velX;
        } else if (this.x + this.width <= gameObject.x) {
            this.x += this.velX;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
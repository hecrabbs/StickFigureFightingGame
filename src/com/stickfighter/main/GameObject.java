package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected Rectangle rect;
    protected boolean falling=true, jumping=false;
    //protected Platform platform;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(double delta);

    public abstract void render(Graphics g, double delta);
    public abstract Rectangle getBounds();//getBounds is in all classes that implement 'GameObject'

    public void follow(GameObject gameObject, double delta) {
        int dx = this.x - gameObject.x;
        int dy = this.y - gameObject.y;
        double direction;
        if (dx == 0 && this.y > gameObject.y) {
            direction = -Math.PI / 2;
        } else if (dx == 0 && this.y < gameObject.y) {
            direction = Math.PI / 2;
        } else {
            direction = Math.atan(((double) dy / (double) dx));
        }

        if (this.x > gameObject.x) {
            direction -= Math.PI;
        }

        this.x += this.velX * Math.cos(direction) * delta;
        this.y += this.velY * Math.sin(direction) * delta;
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

    public boolean isFalling() { return falling; }

    public boolean isJumping() { return jumping; }

    /*
     * int w/h is the width and the height.
     * This method gets the bounds of the rectangle
     * and will be used to find the collisions
     */

    public boolean isColliding(GameObject gameObj) {
        return this.rect.intersects(gameObj.rect);
    }

}
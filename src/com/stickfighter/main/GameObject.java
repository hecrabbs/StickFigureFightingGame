package com.stickfighter.main;

import java.awt.Graphics;

public abstract class GameObject {

    protected int x, y;
    protected ID id;
    protected int velX, velY;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public void follow(GameObject gameObject) {
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

        this.x += this.velX * Math.cos(direction);
        this.y += this.velY * Math.sin(direction);
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

}
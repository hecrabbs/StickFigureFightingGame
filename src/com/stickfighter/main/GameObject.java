package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected int x, y;
    protected int width, height;
    protected ID id;
    protected int velX, velY;
    protected Rectangle clip;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(double delta);

    public abstract void render(Graphics g, double delta);

    public void follow(GameObject gameObj, double delta) {
        int dx = this.x - gameObj.x;
        int dy = this.y - gameObj.y;
        double direction;
        if (dx == 0 && this.y > gameObj.y) {
            direction = -Math.PI / 2;
        } else if (dx == 0 && this.y < gameObj.y) {
            direction = Math.PI / 2;
        } else {
            direction = Math.atan(((double) dy / (double) dx));
        }

        if (this.x > gameObj.x) {
            direction -= Math.PI;
        }

        this.x += this.velX * Math.cos(direction) * delta;
        this.y += this.velY * Math.sin(direction) * delta;
    }

    public boolean detectCollision(GameObject gameObj) {
        return this.clip.intersects(gameObj.clip);

    }

    public void collide(GameObject gameObj) {
        int dx = (this.x + this.width / 2) - (gameObj.x + gameObj.width / 2);
        int dy = (this.y + this.height / 2) - (gameObj.y + gameObj.height / 2);
        double distance = Math.hypot((double) dx, (double) dy);
        double overlapX = this.width / 2 + gameObj.width / 2 - dx;
        double overlapY = this.height / 2 + gameObj.height / 2 - dy;
        if (overlapX > 0) {
            this.x -= overlapX;

        }
        if (overlapY > 0) {
            this.y -= overlapY;

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

}
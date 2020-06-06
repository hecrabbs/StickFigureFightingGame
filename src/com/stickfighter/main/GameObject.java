package com.stickfighter.main;

import java.awt.*;

public abstract class GameObject {

    protected int width, height;
    protected float x, y;
    protected float velX, velY;
    protected ID id;
    protected boolean falling, jumping, knockback;
    protected float gravity = 1;
    protected boolean movingLeft;
    protected boolean movingRight;
    protected boolean facingRight;
    protected boolean isAttacking, shooting, hasGun;
    protected int health;
    protected Rectangle strikeRange;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public Rectangle getBounds() {
        return new Rectangle((int) this.x, (int) this.y, this.width, this.height);
    }

    public Rectangle getBoundsB() {
        return new Rectangle((int) this.x + (this.width / 2) - (this.width / 4), (int) this.y + (this.height / 2), this.width / 2, this.height / 2);
    }

    public Rectangle getBoundsT() {
        return new Rectangle((int) this.x + (this.width / 2) - (this.width / 4), (int) this.y, this.width / 2, this.height / 2);
    }

    public Rectangle getBoundsR() {
        return new Rectangle((int) this.x + this.width - 5, (int) this.y + 5, 5, this.height - 10);
    }

    public Rectangle getBoundsL() {
        return new Rectangle((int) this.x, (int) this.y + 5, 5, this.height - 10);
    }

    public void follow(GameObject gameObj) {
        if (this.x >= gameObj.x + gameObj.width) {
            this.x -= this.velX;
        } else if (this.x + this.width <= gameObj.x) {
            this.x += this.velX;
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setID(ID id) {
        this.id = id;
    }

    public ID getID() {
        return id;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
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

    public int getHealth() {
        return this.health;
    }

    public void setHealth(int h) {
        this.health = h;
    }

    public void collidedDam() {
        this.health--;
    }

    public boolean isFacingRight() {
        return facingRight;
    }

}
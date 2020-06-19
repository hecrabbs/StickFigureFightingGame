package com.stickfighter.main;

import java.awt.*;

public abstract class GameObject {

    protected Handler handler = Game.handler;
    protected Shape collider;
    protected Shape oldCollider;
    protected Shape pathPoly;
    protected ID id;
    protected Rectangle strikeRange;
    protected boolean falling, jumping, fixed, knockback, movingRight, movingLeft, isAttacking, shooting, hasGun, facingRight;
    protected int width, height;
    protected float x, y;
    protected float velX, velY;
    protected float gravity = .9f;
    protected int health;
    protected int ammo;

    private final float DEFAULT_GRAVITY = .9f;
    private final int X_INDEX = 0;
    private final int Y_INDEX = 1;

    public GameObject(int x, int y, ID id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(Double dt);

    public abstract void render(Graphics g);

    public abstract void handleCollisions(Rectangle r, Rectangle oldR, GameObject temp);

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    public Rectangle getBoundsL() {
        return new Rectangle((int) x, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsR() {
        return new Rectangle((int) x + width - 5, (int) y + 5, 5, height - 10);
    }

    public Rectangle getBoundsB() {
        return new Rectangle((int) x + (width / 2) - (width / 4), (int) y + (height / 2), width / 2, height / 2);
    }

    public Rectangle getBoundsT() {
        return new Rectangle((int) x + (width / 2) - (width / 4), (int) y, width / 2, height / 2);
    }

    public void follow(GameObject gameObj) {
        if (x >= gameObj.x + gameObj.width) {
            velX = -4;
        } else if (x + width <= gameObj.x) {
            velX = 4;
        }
    }

    public void setRectCollider() {
        collider = getBounds();
        oldCollider = collider;
    }

    public void setPolyCollider(int[] x, int[] y, int n) {
        collider = new Polygon(x, y, n);
        oldCollider = collider;
    }

    public void updatePosition() {
        x += velX;
        y += velY;
    }

    public void addGravity() {
        if ((falling || jumping) && !fixed) {
            velY += gravity;
        }
    }

    public void checkCollision() {
        if (collider instanceof Rectangle) {
            collider = getBounds();
            Rectangle r = (Rectangle) collider;
            int[] rTL = new int[]{r.x, r.y}; //top left corner
            int[] rTR = new int[]{r.x + r.width, r.y}; //top right corner
            int[] rBR = new int[]{r.x + r.width, r.y + r.height}; //bottom right corner
            int[] rBL = new int[]{r.x, r.y + r.height}; //bottom left corner
            Rectangle oldR = (Rectangle) oldCollider;
            int[] oldTL = new int[]{oldR.x, oldR.y};
            int[] oldTR = new int[]{oldR.x + oldR.width, oldR.y};
            int[] oldBR = new int[]{oldR.x + oldR.width, oldR.y + oldR.height};
            int[] oldBL = new int[]{oldR.x, oldR.y + oldR.height};
            if (r.x < oldR.x) {
                if (r.y < oldR.y) {
                    pathPoly = new Polygon(new int[]{rBL[X_INDEX], rTL[X_INDEX], rTR[X_INDEX], oldTR[X_INDEX], oldBR[X_INDEX], oldBL[X_INDEX]}, new int[]{rBL[Y_INDEX], rTL[Y_INDEX], rTR[Y_INDEX], oldTR[Y_INDEX], oldBR[Y_INDEX], oldBL[Y_INDEX]}, 6);
                } else if (r.y > oldR.y) {
                    pathPoly = new Polygon(new int[]{rBR[X_INDEX], rBL[X_INDEX], rTL[X_INDEX], oldTL[X_INDEX], oldTR[X_INDEX], oldBR[X_INDEX]}, new int[]{rBR[Y_INDEX], rBL[Y_INDEX], rTL[Y_INDEX], oldTL[Y_INDEX], oldTR[Y_INDEX], oldBR[Y_INDEX]}, 6);
                } else {
                    pathPoly = new Rectangle(rTL[X_INDEX], rTL[Y_INDEX], oldTR[X_INDEX] - rTL[X_INDEX], rBL[Y_INDEX] - rTL[Y_INDEX]);
                }
            } else if (r.x > oldR.x) {
                if (r.y < oldR.y) {
                    pathPoly = new Polygon(new int[]{oldBR[X_INDEX], oldBL[X_INDEX], oldTL[X_INDEX], rTL[X_INDEX], rTR[X_INDEX], rBR[X_INDEX]}, new int[]{oldBR[Y_INDEX], oldBL[Y_INDEX], oldTL[Y_INDEX], rTL[Y_INDEX], rTR[Y_INDEX], rBR[Y_INDEX]}, 6);
                } else if (r.y > oldR.y) {
                    pathPoly = new Polygon(new int[]{oldBL[X_INDEX], oldTL[X_INDEX], oldTR[X_INDEX], rTR[X_INDEX], rBR[X_INDEX], rBL[X_INDEX]}, new int[]{oldBL[Y_INDEX], oldTL[Y_INDEX], oldTR[Y_INDEX], rTR[Y_INDEX], rBR[Y_INDEX], rBL[Y_INDEX]}, 6);
                } else {
                    pathPoly = new Rectangle(oldTL[X_INDEX], oldTL[Y_INDEX], rTR[X_INDEX] - oldTL[X_INDEX], rBR[Y_INDEX] - rTR[Y_INDEX]);
                }
            } else if (r.y > oldR.y) {
                pathPoly = new Rectangle(oldTL[X_INDEX], oldTL[Y_INDEX], rBR[X_INDEX] - rBL[X_INDEX], rBL[Y_INDEX] - oldTL[Y_INDEX]);
            } else if (r.y < oldR.y) {
                pathPoly = new Rectangle(rTL[X_INDEX], rTL[Y_INDEX], rTR[X_INDEX] - rTL[X_INDEX], oldBL[Y_INDEX] - rTL[Y_INDEX]);
            } else pathPoly = collider;
            //the big chunk of code above this makes a polygon around the players entire movement path within one tick or frame

            for (int i = 0; i < handler.size(); i++) {
                GameObject temp = handler.getGameObjects().get(i);
                if (pathPoly.intersects(temp.getBounds())) {
                    handlePlatformCollision(r, oldR, temp);
                    handleCollisions(r, oldR, temp);
                } else falling = true;
            }
            collider = getBounds();
            oldCollider = collider;
        }
    }

    public void handlePlatformCollision(Rectangle r, Rectangle oldR, GameObject temp) {
        if (temp.id == ID.Platform) {
            if (oldR.x >= temp.getX() + temp.getWidth()) {//left
                x = temp.x + temp.width;
            } else if (oldR.x + width <= temp.x) {//right
                x = temp.x - width;
            } else if (oldR.y + oldR.height <= temp.getY()) { //bottom
                y = temp.y - height;
                velY = 0;
                falling = false;
                jumping = false;
            } else if (oldR.y >= temp.getY() + temp.getHeight()) {//top
                y = temp.y + temp.height;
                velY = 0;
            }
        }
    }

    public void rebound() {
        if (knockback) {
            velX -= velX / (1 + 20);
            if (Math.abs(velX) <= 5) {
                velX = 0;
                knockback = false;
                movingRight = false;
                movingLeft = false;
            }
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

    public void takeHit(int amount) {
        this.health -= amount;
    }

    public boolean isFacingRight() {
        return facingRight;
    }
}
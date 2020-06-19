package com.stickfighter.main;

import java.awt.*;

public class Enemy extends GameObject {

    private final GameObject player;

    private float theta = 0;

    public Enemy(int x, int y, Handler handler, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
        this.width = 32;
        this.height = 64;
        setRectCollider();
        this.health = 32;
    }

    public void tick(Double dt) {
        addGravity();
        move();
        updatePosition();
        checkCollision();
        rebound();
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(Color.RED);
        g.setColor(Color.RED);
        //enemy's rectangle
        g.fillRect((int) this.x, (int) this.y, this.width, this.height);

        g.setColor(Color.BLACK);
        //old hitbox rectangles
//        g2d.draw(this.getBoundsB());
//        g2d.draw(this.getBoundsT());
//        g2d.draw(this.getBoundsL());
//        g2d.draw(this.getBoundsR());
        //new hitbox shape
        g2d.draw(pathPoly);
        enemyHealth(g);
    }

    public void move() {
        if (!knockback) {
            if (Math.abs(this.x - player.x) <= 400) {
                follow(player);
                theta = (this.x > player.x) ? (float) Math.PI / 2 : (float) (3 * Math.PI / 2);
            } else {
                velX = (float) (2 * Math.sin(theta));
                theta += .02;
            }
        }
    }
    

    public void handleCollisions(Rectangle r, Rectangle oldR, GameObject temp) {
        if (temp.id == ID.Player) {
            temp.health--;
            if (oldR.x >= temp.x + temp.width) {//left
                x = temp.x + temp.width;
                System.out.println("MOVED");
                temp.velY = -10;
                temp.velX = -15;
                temp.knockback = true;
                velX = 10;
                knockback = true;
            } else if (oldR.x + width <= temp.x) {//right
                x = temp.x - width;
                temp.velY = -10;
                temp.velX = 15;
                temp.knockback = true;
                velX = -10;
                knockback = true;
            } else if (oldR.y + oldR.height <= temp.y) { //bottom
                y = temp.y - height;
                velY = 0;
                falling = false;
                jumping = false;
            } else if (oldR.y >= temp.y + temp.height) {//top
                y = temp.y + temp.height;
                velY = 0;
            }
        }
    }

    public void enemyHealth(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect((int) this.x, (int) this.y - 20, width, 7);
        g.setColor(Color.getHSBColor((1f * this.health) / 360, 1f, 1f));
        g.fillRect((int) this.x, (int) this.y - 20, this.health, 7);
        g.setColor(Color.WHITE);
        g.drawRect((int) this.x, (int) this.y - 20, width, 7);
    }

}
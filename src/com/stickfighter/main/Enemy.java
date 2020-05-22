package com.stickfighter.main;

import java.awt.*;

public class Enemy extends GameObject {

    private GameObject player;

    public Enemy(int x, int y, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;

        velX = 2;
        velY = 2;

    }

    public void tick(double delta) {
        follow(this.player, delta);

    }

    public void render(Graphics g, double delta) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 25, 25);

    }

    public Rectangle getBounds() {
        /*
        Enemies should have the same universal size,
        unless we want to make a big guy, but then
        we can just make a new bad guy class
        */
        return new Rectangle(x, y, 25,25);
    }

}
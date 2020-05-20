package com.stickfighter.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);

        this.width = 50;
        this.height = 50;
    }

    public void tick(double delta) {

        this.x += velX * delta;
        this.y += velY * delta;
        // if (y >= 1000) {
        //     this.velY = 0;
        // } else {
        //     this.velY += 1 * delta;
        // }
    }

    public void render(Graphics g, double delta) {
        g.setClip(this.x - this.width / 2, this.y - height / 2, this.width, this.height);
        this.clip = g.getClipBounds();
        g.setColor(Color.WHITE);
        g.fillRect(this.x - this.width / 2, this.y - height / 2, this.width, this.height);
    }

}
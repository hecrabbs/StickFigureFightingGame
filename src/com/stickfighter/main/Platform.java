package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Color;

public class Platform extends GameObject {
    public Platform(int x, int y, ID id) {
        super(x, y, id);

        this.width = 1920;
        this.height = 15;
    }

    public void tick(double delta) {

    }

    public void render(Graphics g, double delta) {
        g.setClip(this.x - this.width / 2, this.y - height / 2, this.width, this.height);
        this.clip = g.getClipBounds();
        g.setColor(Color.YELLOW);
        g.fillRect(this.x - this.width / 2, this.y - height / 2, this.width, this.height);
    }

}
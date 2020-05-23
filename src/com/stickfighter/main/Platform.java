package com.stickfighter.main;

import java.awt.*;

public class Platform extends GameObject{

    public Platform(int x, int y, int width, int height, ID id) {
        super(x, y, id);
        this.width = width;
        this.height = height;

    }

    public void tick(double delta) {
        //Only need to update platform here if it is moving.
    }

    public void render(Graphics g, double delta) {
        g.setColor(Color.YELLOW);
        g.fillRect(this.x,this.y,this.width, this.height);
    }
}
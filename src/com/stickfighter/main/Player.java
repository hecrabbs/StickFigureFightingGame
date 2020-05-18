package com.stickfighter.main;

import java.awt.Color;
import java.awt.Graphics;



public class Player extends GameObject {

    public Player(int x, int y, ID id) {
        super(x, y, id);
    }

    public void tick() {
        x += velX;
        y += velY;

    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 50, 50);

    }

}
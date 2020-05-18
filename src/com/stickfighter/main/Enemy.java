package com.stickfighter.main;

import java.awt.Graphics;
import java.awt.Color;

public class Enemy extends GameObject {

    private GameObject player;

    public Enemy(int x, int y, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;

        velX = 2;
        velY = 2;

    }

    public void tick() {
        follow(this.player);

    }

    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 25, 25);

    }

}
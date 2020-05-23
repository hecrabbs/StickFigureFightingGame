package com.stickfighter.main;

import java.awt.*;

public class Enemy extends GameObject {

    private GameObject player;

    public Enemy(int x, int y, ID id, GameObject player) {
        super(x, y, id);
        this.player = player;
        this.width = 25;
        this.height = 25;
        this.velX = 2;
        this.velY = 2;
    }

    public void tick(double delta) {
        follow(this.player, delta);
    }

    public void render(Graphics g, double delta) {
        g.setColor(Color.RED);
        g.fillRect(this.x, this.y, this.width, this.height);
    }

}